CREATE OR REPLACE FUNCTION lsa.run_lsa_calculated_counts (IN reportId integer, IN project_group_code varchar)
RETURNS void
 LANGUAGE plpgsql
AS $function$
BEGIN

/*
LSA FY2019 Sample Code

Name:  8_9 to 8_21 lsa.lsa_Calculated counts (File 9 of 10)
Date:  4/15/2020
	   5/21/2020 - add set of Step column to all INSERT statements


	8.9 Get Counts of People by Project ID and Household Characteristics
*/
	--Count people in households by ProjectID for:
	--AO/AC/CO/All All: Disabled Adult/HoH, CH Adult/HoH, Adult/HoH Fleeing DV,
	--  and:  AO Youth, AO/AC Vet, AC Youth Parent, CO Parent,
	delete from lsa.lsa_Calculated where ReportRow >= 53;

	insert into lsa.lsa_Calculated
		(Value, Cohort, Universe, HHType
		, Population, SystemPath, ReportRow, ProjectID, ReportID, Step)
	select count (distinct n.PersonalID)
		, cd.Cohort, 10 as Universe
		, coalesce(pop.HHType, 0)
		, pop.PopID as PopID
		, -1, 53
		, n.ProjectID
		, cd.ReportID, '8.9'
	from lsa.tlsa_Enrollment n
	left outer join v2020.service_fa_referral bn on cast (bn.enrollmentid as varchar) = n.EnrollmentID
		and bn.record_type = '200'
		and bn.deleted is false and bn.parent_id is null
	inner join lsa.tlsa_HHID hhid on hhid.HouseholdID = n.HouseholdID
	inner join lsa.ref_Populations pop on
		(hhid.ActiveHHType = pop.HHType or pop.HHType is null)
		and (hhid.HHAdultAge = pop.HHAdultAge or pop.HHAdultAge is null)
		and (hhid.HHVet = pop.HHVet or pop.HHVet is null)
		and (hhid.HHDisability = pop.HHDisability or pop.HHDisability is null)
		and (hhid.HHFleeingDV = pop.HHFleeingDV or pop.HHFleeingDV is null)
		and (hhid.HHParent = pop.HHParent or pop.HHParent is null)
		and (hhid.HHChronic = pop.HHChronic or pop.HHChronic is null)
	inner join lsa.tlsa_CohortDates cd on cd.CohortEnd >= n.EntryDate
		  and (cd.CohortStart < n.ExitDate
			or n.ExitDate is null
			or (n.ProjectType = 13 AND n.ExitDate = cd.CohortStart and n.MoveInDate = cd.CohortStart))
	where n.Active = '1' and cd.Cohort between 1 and 13
		and pop.PopID in (0,1,2,3,4,5,7,8,9,10) and pop.PopType = 1
		and pop.SystemPath is null
		and (
			 --for RRH and PSH, count only people who are housed in period
			(n.ProjectType in (3,13) and n.MoveInDate <= cd.CohortEnd)
			--for night-by-night ES, count only people with bednights in period
			or (n.TrackingMethod = 3 and n.ProjectType = 1
				and bn.DateProvided between cd.CohortStart and cd.CohortEnd
				and bn.DateProvided >= n.EntryDate
				and (bn.DateProvided < n.ExitDate or n.ExitDate is null))
			or (n.TrackingMethod = 0 and n.ProjectType = 1)
			or (n.ProjectType in (2,8))
			)
	group by cd.Cohort, pop.PopID, n.ProjectID, cd.ReportID, pop.HHType;

/*
	8.10 Get Counts of People by Project Type and Household Characteristics
*/
	--Unduplicated count of people in households for each project type
	insert into lsa.lsa_Calculated
		(Value, Cohort, Universe, HHType
		, Population, SystemPath, ReportRow, ReportID, Step)
	select count (distinct n.PersonalID)
		, cd.Cohort, case n.ProjectType
			when 1 then 11
			when 8 then 12
			when 2 then 13
			when 13 then 14
			else 15 end
		, coalesce(pop.HHType, 0)
		, pop.PopID, -1, 53
		, cd.ReportID, '8.10.1'
	from lsa.tlsa_Enrollment n
	left outer join v2020.service_fa_referral bn on cast (bn.enrollmentid as varchar) = n.EnrollmentID
		and bn.record_type = '200'
		and bn.deleted is false and bn.parent_id is null
	inner join lsa.tlsa_HHID hhid on hhid.HouseholdID = n.HouseholdID
	inner join lsa.ref_Populations pop on
		(hhid.ActiveHHType = pop.HHType or pop.HHType is null)
		and (hhid.HHAdultAge = pop.HHAdultAge or pop.HHAdultAge is null)
		and (hhid.HHVet = pop.HHVet or pop.HHVet is null)
		and (hhid.HHDisability = pop.HHDisability or pop.HHDisability is null)
		and (hhid.HHFleeingDV = pop.HHFleeingDV or pop.HHFleeingDV is null)
		and (hhid.HHParent = pop.HHParent or pop.HHParent is null)
		and (hhid.HHChronic = pop.HHChronic or pop.HHChronic is null)
	inner join lsa.tlsa_CohortDates cd on cd.CohortEnd >= n.EntryDate
		  and (cd.CohortStart < n.ExitDate
			or n.ExitDate is null
			or ( n.ProjectType = 13 and n.ExitDate = cd.CohortStart and n.MoveInDate = cd.CohortStart))
	where n.Active = '1' and cd.Cohort between 1 and 13
		and pop.PopID between 0 and 10 and pop.PopType = 1
		and pop.SystemPath is null
		and (
			 --for RRH and PSH, count only people who are housed in period
			(n.ProjectType in (3,13) and n.MoveInDate <= cd.CohortEnd)
			--for night-by-night ES, count only people with bednights in period
			or (n.TrackingMethod = 3 and n.ProjectType = 1
				and bn.DateProvided between cd.CohortStart and cd.CohortEnd
				and bn.DateProvided >= n.EntryDate
				and (bn.DateProvided < n.ExitDate or n.ExitDate is null))
			or (n.TrackingMethod = 0 and n.ProjectType = 1)
			or (n.ProjectType in (2,8))
			)
	group by cd.Cohort, pop.PopID
			, n.ProjectType
			, cd.ReportID
			, pop.HHType;

	--Unduplicated count of people in households for ES/SH/TH combined
	insert into lsa.lsa_Calculated
		(Value, Cohort, Universe, HHType
		, Population, SystemPath, ReportRow, ReportID, Step)
	select count (distinct n.PersonalID)
		, cd.Cohort, 16 as Universe
		, coalesce(pop.HHType, 0)
		, pop.PopID, -1, 53
		, cd.ReportID, '8.10.2'
	from lsa.tlsa_Enrollment n
	left outer join v2020.service_fa_referral bn on cast (bn.enrollmentid as varchar) = n.EnrollmentID
		and bn.record_type = '200'
		and bn.deleted is false and bn.parent_id is null
	inner join lsa.tlsa_HHID hhid on hhid.HouseholdID = n.HouseholdID
	inner join lsa.ref_Populations pop on
		(hhid.ActiveHHType = pop.HHType or pop.HHType is null)
		and (hhid.HHAdultAge = pop.HHAdultAge or pop.HHAdultAge is null)
		and (hhid.HHVet = pop.HHVet or pop.HHVet is null)
		and (hhid.HHDisability = pop.HHDisability or pop.HHDisability is null)
		and (hhid.HHFleeingDV = pop.HHFleeingDV or pop.HHFleeingDV is null)
		and (hhid.HHParent = pop.HHParent or pop.HHParent is null)
		and (hhid.HHChronic = pop.HHChronic or pop.HHChronic is null)
	inner join lsa.tlsa_CohortDates cd on cd.CohortEnd >= n.EntryDate
		  and (cd.CohortStart < n.ExitDate
			or n.ExitDate is null)
	where n.Active = '1' and cd.Cohort between 1 and 13
		and pop.PopID between 0 and 10 and pop.PopType = 1
		and pop.SystemPath is null
		and (
			(n.TrackingMethod = 3 and n.ProjectType = 1
				and bn.DateProvided between cd.CohortStart and cd.CohortEnd
				and bn.DateProvided >= n.EntryDate
				and (bn.DateProvided < n.ExitDate or n.ExitDate is null))
			or (n.TrackingMethod = 0 and n.ProjectType = 1)
			or (n.ProjectType in (2,8))
			)
	group by cd.Cohort, pop.PopID
			, cd.ReportID
			, pop.HHType;

/*
	8.11 Get Counts of Households by Project ID
*/
	--Count households
	insert into lsa.lsa_Calculated
		(Value, Cohort, Universe, HHType
		, Population, SystemPath, ReportRow, ProjectID, ReportID, Step)
	select count (distinct cast(hhid.HouseholdID as varchar) || cast(hhid.ActiveHHType as varchar))
		, cd.Cohort, 10
		, coalesce(pop.HHType, 0)
		, pop.PopID, -1, 54
		, hhid.ProjectID, cd.ReportID, '8.11'
	from lsa.tlsa_HHID hhid
	left outer join v2020.service_fa_referral bn on cast (bn.enrollmentid as varchar) = hhid.EnrollmentID
		and bn.record_type = '200'
		and bn.deleted is false and bn.parent_id is null
	inner join lsa.ref_Populations pop on
		(hhid.ActiveHHType = pop.HHType or pop.HHType is null)
		and (hhid.HHAdultAge = pop.HHAdultAge or pop.HHAdultAge is null)
		and (hhid.HHVet = pop.HHVet or pop.HHVet is null)
		and (hhid.HHDisability = pop.HHDisability or pop.HHDisability is null)
		and (hhid.HHFleeingDV = pop.HHFleeingDV or pop.HHFleeingDV is null)
		and (hhid.HHParent = pop.HHParent or pop.HHParent is null)
		and (hhid.HHChronic = pop.HHChronic or pop.HHChronic is null)
	inner join lsa.tlsa_CohortDates cd on cd.CohortEnd >= hhid.EntryDate
		  and (cd.CohortStart < hhid.ExitDate
			or hhid.ExitDate is null
			or (hhid.ProjectType = 13 OR  hhid.ExitDate = cd.CohortStart and hhid.MoveInDate = cd.CohortStart))
	where hhid.Active = '1' and cd.Cohort between 1 and 13
		and pop.PopID in (0,1,2,3,4,5,7,8,9,10) and pop.PopType = 1
		and pop.SystemPath is null
		and (
			 --for RRH and PSH, count only people who are housed in period
			(hhid.ProjectType in (3,13) and hhid.MoveInDate <= cd.CohortEnd)
			--for night-by-night ES, count only people with bednights in period
			or (hhid.TrackingMethod = 3 and hhid.ProjectType = 1
				and bn.DateProvided between cd.CohortStart and cd.CohortEnd
				and bn.DateProvided >= hhid.EntryDate
				and (bn.DateProvided < hhid.ExitDate or hhid.ExitDate is null))
			or (hhid.TrackingMethod = 0 and hhid.ProjectType = 1)
			or (hhid.ProjectType in (2,8))
			)
	group by cd.Cohort, pop.PopID, hhid.ProjectID, cd.ReportID
		, pop.HHType;


/*
	8.12 Get Counts of Households by Project Type
*/
--Unduplicated count households for each project type
	insert into lsa.lsa_Calculated
		(Value, Cohort, Universe, HHType
		, Population, SystemPath, ReportRow, ReportID, Step)
	select count (distinct cast(hhid.HouseholdID as varchar) || cast(hhid.ActiveHHType as varchar))
		, cd.Cohort, case hhid.ProjectType
			when 1 then 11
			when 8 then 12
			when 2 then 13
			when 13 then 14
			else 15 end as Universe
		, coalesce(pop.HHType, 0) as HHType
		, pop.PopID, -1, 54
		, cd.ReportID, '8.12.1'
	from lsa.tlsa_HHID hhid
	left outer join v2020.service_fa_referral bn on cast (bn.enrollmentid as varchar) = hhid.EnrollmentID
		and bn.record_type ='200'
		and bn.deleted is false and bn.parent_id is null
	inner join lsa.ref_Populations pop on
		(hhid.ActiveHHType = pop.HHType or pop.HHType is null)
		and (hhid.HHAdultAge = pop.HHAdultAge or pop.HHAdultAge is null)
		and (hhid.HHVet = pop.HHVet or pop.HHVet is null)
		and (hhid.HHDisability = pop.HHDisability or pop.HHDisability is null)
		and (hhid.HHFleeingDV = pop.HHFleeingDV or pop.HHFleeingDV is null)
		and (hhid.HHParent = pop.HHParent or pop.HHParent is null)
		and (hhid.HHChronic = pop.HHChronic or pop.HHChronic is null)
	inner join lsa.tlsa_CohortDates cd on cd.CohortEnd >= hhid.EntryDate
		  and (cd.CohortStart < hhid.ExitDate
			or hhid.ExitDate is null
			or (hhid.ProjectType = 13 and hhid.ExitDate = cd.CohortStart and hhid.MoveInDate = cd.CohortStart))
	where hhid.Active = '1' and cd.Cohort between 1 and 13
		and pop.PopID between 0 and 10 and pop.PopType = 1
		and pop.SystemPath is null
		and (
			 --for RRH and PSH, count only people who are housed in period
			(hhid.ProjectType in (3,13) and hhid.MoveInDate <= cd.CohortEnd)
			--for night-by-night ES, count only people with bednights in period
			or (hhid.TrackingMethod = 3 and hhid.ProjectType = 1
				and bn.DateProvided between cd.CohortStart and cd.CohortEnd
				and bn.DateProvided >= hhid.EntryDate
				and (bn.DateProvided < hhid.ExitDate or hhid.ExitDate is null))
			or (hhid.TrackingMethod = 0 and hhid.ProjectType = 1)
			or (hhid.ProjectType in (2,8))
			)
	group by cd.Cohort, pop.PopID, case hhid.ProjectType
			when 1 then 11
			when 8 then 12
			when 2 then 13
			when 13 then 14
			else 15 end, cd.ReportID
		, pop.HHType ;

	--Unduplicated count of households for ES/SH/TH combined
	insert into lsa.lsa_Calculated
		(Value, Cohort, Universe, HHType
		, Population, SystemPath, ReportRow, ReportID, Step)
	select count (distinct cast(hhid.HouseholdID as varchar) || cast(hhid.ActiveHHType as varchar))
		, cd.Cohort, 16 as Universe
		, coalesce(pop.HHType, 0) as HHType
		, pop.PopID, -1, 54
		, cd.ReportID, '8.12.2'
	from lsa.tlsa_HHID hhid
	left outer join v2020.service_fa_referral bn on cast (bn.enrollmentid as varchar) = hhid.EnrollmentID
		and bn.record_type = '200'
		and bn.deleted is false and bn.parent_id is null
	inner join lsa.ref_Populations pop on
		(hhid.ActiveHHType = pop.HHType or pop.HHType is null)
		and (hhid.HHAdultAge = pop.HHAdultAge or pop.HHAdultAge is null)
		and (hhid.HHVet = pop.HHVet or pop.HHVet is null)
		and (hhid.HHDisability = pop.HHDisability or pop.HHDisability is null)
		and (hhid.HHFleeingDV = pop.HHFleeingDV or pop.HHFleeingDV is null)
		and (hhid.HHParent = pop.HHParent or pop.HHParent is null)
		and (hhid.HHChronic = pop.HHChronic or pop.HHChronic is null)
	inner join lsa.tlsa_CohortDates cd on cd.CohortEnd >= hhid.EntryDate
		  and (cd.CohortStart < hhid.ExitDate
			or hhid.ExitDate is null)
	where hhid.Active = '1' and cd.Cohort between 1 and 13
		and pop.PopID between 0 and 10 and pop.PopType = 1
		and pop.SystemPath is null
		and (--for night-by-night ES, count only people with bednights in period
			(hhid.TrackingMethod = 3 and hhid.ProjectType = 1
				and bn.DateProvided between cd.CohortStart and cd.CohortEnd
				and bn.DateProvided >= hhid.EntryDate
				and (bn.DateProvided < hhid.ExitDate or hhid.ExitDate is null))
			or (hhid.TrackingMethod = 0 and hhid.ProjectType = 1)
			or (hhid.ProjectType in (2,8))
			)
	group by cd.Cohort, pop.PopID, cd.ReportID
		, pop.HHType ;

/*
	8.13 Get Counts of People by ProjectID and Personal Characteristics
*/
	--Count people with specific characteristic
	insert into lsa.lsa_Calculated
		(Value, Cohort, Universe, HHType
		, Population, SystemPath, ReportRow, ProjectID, ReportID, Step)
	select count (distinct lp.PersonalID)
		, cd.Cohort, 10
		, coalesce(pop.HHType, 0) as HHType
		, pop.PopID, -1, 55
		, n.ProjectID, cd.ReportID, '8.13'
	from lsa.tlsa_Person lp
	inner join lsa.tlsa_Enrollment n on n.PersonalID = lp.PersonalID
	left outer join v2020.service_fa_referral bn on CAST (bn.enrollmentid AS varchar) = n.EnrollmentID
		and bn.record_type = '200'
		and bn.deleted is false and bn.parent_id is null
	inner join lsa.tlsa_HHID hhid on hhid.HouseholdID = n.HouseholdID
	inner join lsa.ref_Populations pop on
		(hhid.ActiveHHType = pop.HHType or pop.HHType is null)
		and (hhid.HHAdultAge = pop.HHAdultAge or pop.HHAdultAge is null)
		and (hhid.HHParent = pop.HHParent or pop.HHParent is null)
		and ((lp.CHTime = pop.CHTime and lp.CHTimeStatus = pop.CHTimeStatus)
			 or pop.CHTime is null)
		and (lp.DisabilityStatus = pop.DisabilityStatus or pop.DisabilityStatus is null)
		and (lp.VetStatus = pop.VetStatus or pop.VetStatus is null)
		and (n.ActiveAge = pop.Age or pop.Age is null)
	left outer join (select n.PersonalID, hhid.ActiveHHType, hhid.ProjectID, max(n.ActiveAge) as Age
		from lsa.tlsa_Enrollment n
		inner join lsa.tlsa_HHID hhid on hhid.HouseholdID = n.HouseholdID
		group by n.PersonalID, hhid.ActiveHHType, hhid.ProjectID
		) latest on latest.PersonalID = n.PersonalID and latest.ActiveHHType = hhid.ActiveHHType
			and latest.ProjectID = hhid.ProjectID and latest.Age = n.ActiveAge
	inner join lsa.tlsa_CohortDates cd on cd.CohortEnd >= n.EntryDate
		  and (cd.CohortStart < n.ExitDate
			or n.ExitDate is null
			or (n.ProjectType = 13 and n.ExitDate = cd.CohortStart and n.MoveInDate = cd.CohortStart))
	where n.Active = '1' and cd.Cohort between 1 and 13
		and (pop.PopID in (3,6) or pop.PopID between 145 and 148)
		and pop.PopType = 3
		and pop.ProjectLevelCount = 1
		and (
			 --for RRH and PSH, count only people who are housed in period
			(n.ProjectType in (3,13) and n.MoveInDate <= cd.CohortEnd)
			--for night-by-night ES, count only people with bednights in period
			or (n.TrackingMethod = 3 and n.ProjectType = 1
				and bn.DateProvided between cd.CohortStart and cd.CohortEnd
				and bn.DateProvided >= n.EntryDate
				and (bn.DateProvided < n.ExitDate or n.ExitDate is null))
			or (n.TrackingMethod = 0 and n.ProjectType = 1)
			or (n.ProjectType in (2,8))
			)
		and
		( pop.PopID < 145 OR latest.Age is not null)
	group by cd.Cohort, pop.PopID, n.ProjectID, cd.ReportID
		, pop.HHType;

/*
	8.14 Get Counts of People by Project Type and Personal Characteristics
*/
	--Count people with specific characteristics for each project type
	insert into lsa.lsa_Calculated
		(Value, Cohort, Universe, HHType
		, Population, SystemPath, ReportRow, ReportID, Step)
	select count (distinct lp.PersonalID)
		, cd.Cohort, case n.ProjectType
			when 1 then 11
			when 8 then 12
			when 2 then 13
			when 13 then 14
			else 15 end
		, coalesce(pop.HHType, 0) as HHType
		, pop.PopID, -1, 55
		, cd.ReportID, '8.14.1'
	from lsa.tlsa_Person lp
	inner join lsa.tlsa_Enrollment n on n.PersonalID = lp.PersonalID
	left outer join v2020.service_fa_referral bn on CAST (bn.enrollmentid AS varchar) = n.EnrollmentID
		and bn.record_type = '200'
		and bn.deleted is false and bn.parent_id is null
	inner join lsa.tlsa_HHID hhid on hhid.HouseholdID = n.HouseholdID
	inner join lsa.ref_Populations pop on
		(hhid.ActiveHHType = pop.HHType or pop.HHType is null)
		and (hhid.HHAdultAge = pop.HHAdultAge or pop.HHAdultAge is null)
		and ((lp.CHTime = pop.CHTime and lp.CHTimeStatus = pop.CHTimeStatus)
			 or pop.CHTime is null)
		and (lp.DisabilityStatus = pop.DisabilityStatus or pop.DisabilityStatus is null)
		and (hhid.HHFleeingDV = pop.HHFleeingDV or pop.HHFleeingDV is null)
		and (hhid.HHParent = pop.HHParent or pop.HHParent is null)
		and (lp.VetStatus = pop.VetStatus or pop.VetStatus is null)
		and (n.ActiveAge = pop.Age or pop.Age is null)
		and (lp.Gender = pop.Gender or pop.Gender is null)
		and (lp.Race = pop.Race or pop.Race is null)
		and (lp.Ethnicity = pop.Ethnicity or pop.Ethnicity is null)
	left outer join (select n.PersonalID, hhid.ActiveHHType as HHType, hhid.ProjectType, max(n.ActiveAge) as Age
		from lsa.tlsa_Enrollment n
		inner join lsa.tlsa_HHID hhid on hhid.HouseholdID = n.HouseholdID
		group by n.PersonalID, hhid.ActiveHHType, hhid.ProjectType
		) latest on latest.PersonalID = n.PersonalID and latest.HHType = hhid.ActiveHHType
			and latest.ProjectType = hhid.ProjectType and latest.Age = n.ActiveAge
	inner join lsa.tlsa_CohortDates cd on cd.CohortEnd >= n.EntryDate
		  and (cd.CohortStart < n.ExitDate
			or n.ExitDate is null
			or ( n.ProjectType = 13 AND n.ExitDate = cd.CohortStart and n.MoveInDate = cd.CohortStart))
	where n.Active = '1' and cd.Cohort between 1 and 13
		and pop.PopType = 3
		and (
			 --for RRH and PSH, count only people who are housed in period
			(n.ProjectType in (3,13) and n.MoveInDate <= cd.CohortEnd)
			--for night-by-night ES, count only people with bednights in period
			or (n.TrackingMethod = 3 and n.ProjectType = 1
				and bn.dateprovided between cd.CohortStart and cd.CohortEnd
				and bn.DateProvided >= n.EntryDate
				and (bn.DateProvided < n.ExitDate or n.ExitDate is null))
			or (n.TrackingMethod = 0 and n.ProjectType = 1)
			or (n.ProjectType in (2,8))
			)
		and
		(
			(pop.PopID not between 24 and 34 and pop.PopID not between 145 and 148)
			or
			(latest.Age is not null)
			)
	group by cd.Cohort, pop.PopID, n.ProjectType, cd.ReportID
		, pop.HHType;

	--Count people with specific characteristics for ES/SH/TH combined
	insert into lsa.lsa_Calculated
		(Value, Cohort, Universe, HHType
		, Population, SystemPath, ReportRow, ReportID, Step)
	select count (distinct lp.PersonalID)
		, cd.Cohort, 16
		, coalesce(pop.HHType, 0) as HHType
		, pop.PopID, -1, 55
		, cd.ReportID, '8.14.3'
	from lsa.tlsa_Person lp
	inner join lsa.tlsa_Enrollment n on n.PersonalID = lp.PersonalID
	left outer join v2020.service_fa_referral bn on cast (bn.enrollmentid as varchar) = n.EnrollmentID
		and bn.record_type = '200'
		and bn.deleted is false and bn.parent_id is null
	inner join lsa.tlsa_HHID hhid on hhid.HouseholdID = n.HouseholdID
	inner join lsa.ref_Populations pop on
		(hhid.ActiveHHType = pop.HHType or pop.HHType is null)
		and (hhid.HHAdultAge = pop.HHAdultAge or pop.HHAdultAge is null)
		and ((lp.CHTime = pop.CHTime and lp.CHTimeStatus = pop.CHTimeStatus)
			 or pop.CHTime is null)
		and (lp.DisabilityStatus = pop.DisabilityStatus or pop.DisabilityStatus is null)
		and (hhid.HHFleeingDV = pop.HHFleeingDV or pop.HHFleeingDV is null)
		and (hhid.HHParent = pop.HHParent or pop.HHParent is null)
		and (lp.VetStatus = pop.VetStatus or pop.VetStatus is null)
		and (n.ActiveAge = pop.Age or pop.Age is null)
		and (lp.Gender = pop.Gender or pop.Gender is null)
		and (lp.Race = pop.Race or pop.Race is null)
		and (lp.Ethnicity = pop.Ethnicity or pop.Ethnicity is null)
	left outer join (select n.PersonalID, hhid.ActiveHHType as HHType, hhid.ProjectType, max(n.ActiveAge) as Age
		from lsa.tlsa_Enrollment n
		inner join lsa.tlsa_HHID hhid on hhid.HouseholdID = n.HouseholdID
		where hhid.ProjectType in (1,2,8)
		group by n.PersonalID, hhid.ActiveHHType, hhid.ProjectType
		) latest on latest.PersonalID = n.PersonalID and latest.HHType = hhid.ActiveHHType
			and latest.ProjectType = hhid.ProjectType and latest.Age = n.ActiveAge
	inner join lsa.tlsa_CohortDates cd on cd.CohortEnd >= n.EntryDate
		  and (cd.CohortStart < n.ExitDate
			or n.ExitDate is null)
	where n.Active = '1' and cd.Cohort between 1 and 13
		and pop.PopType = 3 and pop.PopID not between 113 and 144
		and (
			--for night-by-night ES, count only people with bednights in period
			(n.TrackingMethod = 3 and n.ProjectType = 1
				and bn.DateProvided between cd.CohortStart and cd.CohortEnd
				and bn.DateProvided >= n.EntryDate
				and (bn.DateProvided < n.ExitDate or n.ExitDate is null))
			or (n.TrackingMethod = 0 and n.ProjectType = 1)
			or (n.ProjectType in (2,8))
			)
		and (
			(pop.PopID not between 24 and 34 and pop.PopID not between 145 and 148)
			or
			(latest.Age is not null)
			)
	group by cd.Cohort, pop.PopID, cd.ReportID
		, pop.HHType;

/*
	8.15 Get Counts of Bed Nights in Report Period by Project ID
*/
	insert into lsa.lsa_Calculated
		(Value, Cohort, Universe, HHType
		, Population, SystemPath, ReportRow, ProjectID, ReportID, Step)
	select count (distinct n.PersonalID || cast(est.theDate as varchar))
		+ count (distinct n.PersonalID || cast(rrhpsh.theDate as varchar))
		+ count (distinct n.PersonalID || cast(bnd.theDate as varchar))
		, 1, 10, coalesce(pop.HHType, 0)
		, pop.PopID, -1, 56
		, n.ProjectID
		, rpt.ReportID, '8.15'
	from lsa.tlsa_Enrollment n
	inner join lsa.tlsa_HHID hhid on hhid.HouseholdID = n.HouseholdID
	inner join lsa.ref_Populations pop on
		(hhid.ActiveHHType = pop.HHType or pop.HHType is null)
		and (hhid.HHAdultAge = pop.HHAdultAge or pop.HHAdultAge is null)
	left outer join v2020.service_fa_referral bn on CAST (bn.enrollmentid AS varchar)= n.EnrollmentID
		and bn.record_type = '200'
		and bn.deleted is false
		and bn.parent_id is null
	inner join lsa.lsa_Report rpt on rpt.ReportEnd >= n.EntryDate AND rpt.project_group_code=bn.project_group_code
	left outer join lsa.ref_Calendar est on est.theDate >= n.EntryDate
		and est.theDate >= rpt.ReportStart
		and est.theDate < coalesce(n.ExitDate, dateadd('d', 1, rpt.ReportEnd))
		and ((n.ProjectType = 1 and n.TrackingMethod = 0)
				or n.ProjectType in (2,8))
	left outer join lsa.ref_Calendar rrhpsh on rrhpsh.theDate >= n.MoveInDate
		and rrhpsh.theDate >= rpt.ReportStart
		and rrhpsh.theDate < coalesce(n.ExitDate, dateadd('d', 1, rpt.ReportEnd))
		and n.ProjectType in (3,13)
	left outer join lsa.ref_Calendar bnd on bnd.theDate = bn.DateProvided
		and bnd.theDate >= rpt.ReportStart and bnd.theDate <= rpt.ReportEnd
		and n.ProjectType = 1 and n.TrackingMethod = 3
	where n.Active = '1' and  pop.PopID in (0,1,2) and pop.SystemPath is null and pop.PopType = 1
	group by n.ProjectID, rpt.ReportID, pop.PopID, pop.HHType;

/*
	8.16 Get Counts of Bed Nights in Report Period by Project Type
*/
	insert into lsa.lsa_Calculated
		(Value, Cohort, Universe, HHType
		, Population, SystemPath, ReportRow, ReportID, Step)
	select count (distinct n.PersonalID || cast(est.theDate as varchar))
		+ count (distinct n.PersonalID || cast(rrhpsh.theDate as varchar))
		+ count (distinct n.PersonalID || cast(bnd.theDate as varchar))
		, 1, case n.ProjectType
			when 1 then 11
			when 8 then 12
			when 2 then 13
			when 13 then 14
			else 15 end
		, coalesce(pop.HHType, 0)
		, pop.PopID, -1, 56
		, rpt.ReportID, '8.16.1'
	from lsa.tlsa_Enrollment n
	inner join lsa.tlsa_HHID hhid on hhid.HouseholdID = n.HouseholdID
	inner join lsa.ref_Populations pop on
		(hhid.ActiveHHType = pop.HHType or pop.HHType is null)
		and (hhid.HHAdultAge = pop.HHAdultAge or pop.HHAdultAge is null)
	left outer join v2020.service_fa_referral bn on CAST (bn.enrollmentid as varchar) = n.EnrollmentID
		and bn.record_type = '200'
		and bn.deleted is false and bn.parent_id is null
	inner join lsa.lsa_Report rpt on rpt.ReportEnd >= n.EntryDate AND rpt.project_group_code=bn.project_group_code
	left outer join lsa.ref_Calendar est on est.theDate >= n.EntryDate
		and est.theDate >= rpt.ReportStart
		and est.theDate < coalesce(n.ExitDate, dateadd('d', 1, rpt.ReportEnd))
		and ((n.ProjectType = 1 and n.TrackingMethod = 0)
				or n.ProjectType in (2,8))
	left outer join lsa.ref_Calendar rrhpsh on rrhpsh.theDate >= n.MoveInDate
		and rrhpsh.theDate >= rpt.ReportStart
		and rrhpsh.theDate < coalesce(n.ExitDate, dateadd('d', 1, rpt.ReportEnd))
		and n.ProjectType in (3,13)
	left outer join lsa.ref_Calendar bnd on bnd.theDate = bn.dateprovided
		and bnd.theDate >= rpt.ReportStart and bnd.theDate <= rpt.ReportEnd
		and n.ProjectType = 1 and n.TrackingMethod = 3
	where n.Active = '1' and pop.PopID in (0,1,2) and pop.SystemPath is null and pop.PopType = 1
	group by rpt.ReportID, pop.PopID, pop.HHType, n.ProjectType;

	insert into lsa.lsa_Calculated
		(Value, Cohort, Universe, HHType
		, Population, SystemPath, ReportRow, ReportID, Step)
	select count (distinct n.PersonalID || cast(est.theDate as varchar))
		+ count (distinct n.PersonalID || cast(bnd.theDate as varchar))
		, 1, 16
		, coalesce(pop.HHType, 0)
		, pop.PopID, -1, 56
		, rpt.ReportID, '8.16.2'
	from lsa.tlsa_Enrollment n
	inner join lsa.tlsa_HHID hhid on hhid.HouseholdID = n.HouseholdID
	inner join lsa.ref_Populations pop on
		(hhid.ActiveHHType = pop.HHType or pop.HHType is null)
		and (hhid.HHAdultAge = pop.HHAdultAge or pop.HHAdultAge is null)
	left outer join v2020.service_fa_referral bn on CAST (bn.enrollmentid AS varchar) = n.EnrollmentID
		and bn.record_type = '200'
		and bn.deleted is false and bn.parent_id is null
	inner join lsa.lsa_Report rpt on rpt.ReportEnd >= n.EntryDate AND rpt.project_group_code=bn.project_group_code
	left outer join lsa.ref_Calendar est on est.theDate >= n.EntryDate
		and est.theDate >= rpt.ReportStart
		and est.theDate < coalesce(n.ExitDate, dateadd('d', 1, rpt.ReportEnd))
		and ((n.ProjectType = 1 and n.TrackingMethod = 0)
				or n.ProjectType in (2,8))
	left outer join lsa.ref_Calendar bnd on bnd.theDate = bn.DateProvided
		and bnd.theDate >= rpt.ReportStart and bnd.theDate <= rpt.ReportEnd
		and n.ProjectType = 1 and n.TrackingMethod = 3
	where n.Active = '1' and pop.PopID in (0,1,2) and pop.SystemPath is null and pop.PopType = 1
		and n.ProjectType in (1,8,2)
	group by rpt.ReportID, pop.PopID, pop.HHType;

/*
	8.17 Get Counts of Bed Nights in Report Period by Project ID/Personal Char
*/
	insert into lsa.lsa_Calculated
		(Value, Cohort, Universe, HHType
		, Population, SystemPath, ReportRow, ProjectID, ReportID, Step)
	select count (distinct n.PersonalID || cast(est.theDate as varchar))
		+ count (distinct n.PersonalID || cast(rrhpsh.theDate as varchar))
		+ count (distinct n.PersonalID || cast(bnd.theDate as varchar))
		, 1, 10, coalesce(pop.HHType, 0)
		, pop.PopID, -1, 57
		, n.ProjectID
		, rpt.ReportID, '8.17'
	from lsa.tlsa_Enrollment n
	inner join lsa.tlsa_Person lp on lp.PersonalID = n.PersonalID
	inner join lsa.tlsa_HHID hhid on hhid.HouseholdID = n.HouseholdID
	inner join lsa.ref_Populations pop on
		(hhid.ActiveHHType = pop.HHType or pop.HHType is null)
		and ((lp.CHTime = pop.CHTime and lp.CHTimeStatus = pop.CHTimeStatus)
			 or pop.CHTime is null)
		and (lp.DisabilityStatus = pop.DisabilityStatus or pop.DisabilityStatus is null)
		and (lp.VetStatus = pop.VetStatus or pop.VetStatus is null)
	left outer join v2020.service_fa_referral bn on CAST (bn.enrollmentid AS varchar) = n.EnrollmentID
		and bn.record_type = '200'
		and bn.deleted is false and bn.parent_id is null
	inner join lsa.lsa_Report rpt on rpt.ReportEnd >= n.EntryDate AND rpt.project_group_code=bn.project_group_code
	left outer join lsa.ref_Calendar est on est.theDate >= n.EntryDate
		and est.theDate >= rpt.ReportStart
		and est.theDate < coalesce(n.ExitDate, dateadd('d', 1, rpt.ReportEnd))
		and ((n.ProjectType = 1 and n.TrackingMethod = 0)
				or n.ProjectType in (2,8))
	left outer join lsa.ref_Calendar rrhpsh on rrhpsh.theDate >= n.MoveInDate
		and rrhpsh.theDate >= rpt.ReportStart
		and rrhpsh.theDate < coalesce(n.ExitDate, dateadd('d', 1, rpt.ReportEnd))
		and n.ProjectType in (3,13)
	left outer join lsa.ref_Calendar bnd on bnd.theDate = bn.DateProvided
		and bnd.theDate >= rpt.ReportStart and bnd.theDate <= rpt.ReportEnd
		and n.ProjectType = 1 and n.TrackingMethod = 3
	where n.Active = '1' and pop.PopID in (3,6) and pop.PopType = 3
	group by n.ProjectID, rpt.ReportID, pop.PopID, pop.HHType;

/*
	8.18 Get Counts of Bed Nights in Report Period by Project Type/Personal Char
*/
	insert into lsa.lsa_Calculated
		(Value, Cohort, Universe, HHType
		, Population, SystemPath, ReportRow, ReportID, Step)
	select count (distinct n.PersonalID || cast(est.theDate as varchar))
		+ count (distinct n.PersonalID || cast(rrhpsh.theDate as varchar))
		+ count (distinct n.PersonalID || cast(bnd.theDate as varchar))
		, 1, case n.ProjectType
			when 1 then 11
			when 8 then 12
			when 2 then 13
			when 13 then 14
			else 15 end
		, coalesce(pop.HHType, 0)
		, pop.PopID, -1, 57
		, rpt.ReportID, '8.18.1'
	from lsa.tlsa_Enrollment n
	inner join lsa.tlsa_Person lp on lp.PersonalID = n.PersonalID
	inner join lsa.tlsa_HHID hhid on hhid.HouseholdID = n.HouseholdID
	inner join lsa.ref_Populations pop on
		(hhid.ActiveHHType = pop.HHType or pop.HHType is null)
		and ((lp.CHTime = pop.CHTime and lp.CHTimeStatus = pop.CHTimeStatus)
			 or pop.CHTime is null)
		and (lp.DisabilityStatus = pop.DisabilityStatus or pop.DisabilityStatus is null)
		and (lp.VetStatus = pop.VetStatus or pop.VetStatus is null)
	left outer join v2020.service_fa_referral bn on CAST (bn.enrollmentid AS varchar) = n.EnrollmentID
		and bn.record_type = '200'
		and bn.deleted is false and bn.parent_id is null
	inner join lsa.lsa_Report rpt on rpt.ReportEnd >= n.EntryDate AND rpt.project_group_code=bn.project_group_code
	left outer join lsa.ref_Calendar est on est.theDate >= n.EntryDate
		and est.theDate >= rpt.ReportStart
		and est.theDate < coalesce(n.ExitDate, dateadd('d', 1, rpt.ReportEnd))
		and ((n.ProjectType = 1 and n.TrackingMethod = 0)
				or n.ProjectType in (2,8))
	left outer join lsa.ref_Calendar rrhpsh on rrhpsh.theDate >= n.MoveInDate
		and rrhpsh.theDate >= rpt.ReportStart
		and rrhpsh.theDate < coalesce(n.ExitDate, dateadd('d', 1, rpt.ReportEnd))
		and n.ProjectType in (3,13)
	left outer join lsa.ref_Calendar bnd on bnd.theDate = bn.DateProvided
		and bnd.theDate >= rpt.ReportStart and bnd.theDate <= rpt.ReportEnd
		and n.ProjectType = 1 and n.TrackingMethod = 3
	where n.Active = '1' and pop.PopID in (3,6) and pop.PopType = 3
	group by rpt.ReportID, pop.PopID, pop.HHType, n.ProjectType;

	--ES/SH/TH unduplicated
	insert into lsa.lsa_Calculated
		(Value, Cohort, Universe, HHType
		, Population, SystemPath, ReportRow, ReportID, Step)
	select count (distinct n.PersonalID || cast(est.theDate as varchar))
		+ count (distinct n.PersonalID || cast(bnd.theDate as varchar))
		, 1, 16
		, coalesce(pop.HHType, 0)
		, pop.PopID, -1, 57
		, rpt.ReportID, '8.16.2'
	from lsa.tlsa_Enrollment n
	inner join lsa.tlsa_Person lp on lp.PersonalID = n.PersonalID
	inner join lsa.tlsa_HHID hhid on hhid.HouseholdID = n.HouseholdID
	inner join lsa.ref_Populations pop on
		(hhid.ActiveHHType = pop.HHType or pop.HHType is null)
		and ((lp.CHTime = pop.CHTime and lp.CHTimeStatus = pop.CHTimeStatus)
			 or pop.CHTime is null)
		and (lp.DisabilityStatus = pop.DisabilityStatus or pop.DisabilityStatus is null)
		and (lp.VetStatus = pop.VetStatus or pop.VetStatus is null)
	left outer join v2020.service_fa_referral bn on CAST (bn.enrollmentid AS varchar)= n.EnrollmentID
		and bn.record_type = '200'
		and bn.deleted is false and bn.parent_id is null
	inner join lsa.lsa_Report rpt on rpt.ReportEnd >= n.EntryDate AND rpt.project_group_code=bn.project_group_code
	left outer join lsa.ref_Calendar est on est.theDate >= n.EntryDate
		and est.theDate >= rpt.ReportStart
		and est.theDate < coalesce(n.ExitDate, dateadd('d', 1, rpt.ReportEnd))
		and ((n.ProjectType = 1 and n.TrackingMethod = 0)
				or n.ProjectType in (2,8))
	left outer join lsa.ref_Calendar bnd on bnd.theDate = bn.DateProvided
		and bnd.theDate >= rpt.ReportStart and bnd.theDate <= rpt.ReportEnd
		and n.ProjectType = 1 and n.TrackingMethod = 3
	where n.Active = '1' and pop.PopID in (3,6) and pop.PopType = 3
		and n.ProjectType in (1,8,2)
	group by rpt.ReportID, pop.PopID, pop.HHType;

/*
	8.19 Get Counts of Enrollments Active after Operating End Date by ProjectID
*/
	insert into lsa.lsa_Calculated
		(Value, Cohort, Universe, HHType
		, Population, SystemPath, ReportRow, ProjectID, ReportID, Step)
	select count (distinct n.EnrollmentID), 20, 10, 0, 0, -1
		, case when hx.exitdate is null then 58
			else 59 end
		, p.id, cd.ReportID, '8.19'
	from lsa.tlsa_Enrollment n
	left outer join v2020.exit hx on cast (hx.enrollmentid as varchar) = n.EnrollmentID
	    and hx.deleted is false and hx.parent_id is null
	inner join v2020.project p on CAST(p.id AS varchar) = n.ProjectID and p.deleted is false and p.parent_id is null
	inner join lsa.tlsa_CohortDates cd on cd.Cohort = 20 and p.operatingenddate between cd.CohortStart and cd.CohortEnd
	where (hx.exitdate is null or hx.exitdate > p.operatingenddate)
		and p.projecttype in ('1','2','3','8','13')
	group by case when hx.exitdate is null then 58
			else 59 end
		, p.id, cd.ReportID;

/*
	8.20 Get Counts of Night-by-Night Enrollments with Exit Date Discrepancies
*/
	insert into lsa.lsa_Calculated
		(Value, Cohort, Universe, HHType
		, Population, SystemPath, ReportRow, ProjectID, ReportID, Step)
	select count (distinct hn.id), 20, 10, 0, 0, -1
		, case when hx.exitdate is null then 60
			else 61 end
		, p.id, cd.ReportID, '8.20'
	from lsa.tlsa_Enrollment n
	inner join lsa.tlsa_CohortDates cd on cd.Cohort = 20
	inner join lsa.tlsa_HHID hhid on hhid.HouseholdID = n.HouseholdID
	inner join v2020.enrollment hn on CAST(hn.id AS varchar) = n.EnrollmentID and hn.deleted is false and hn.parent_id is null
	left outer join v2020.exit hx on hx.enrollmentid = hn.id
	  and hx.deleted is false and hx.parent_id is null
	inner join v2020.project p on p.id = hn.projectid and p.projecttype = '1' and p.trackingmethod = '3' and p.continuumproject = '1' and p.deleted is false and p.parent_id is null
	left outer join (select distinct svc.enrollmentid, max(svc.dateprovided) as LastBednight
		from v2020.service_fa_referral svc
		inner join v2020.enrollment nbn on nbn.id = svc.enrollmentid and nbn.deleted is false and nbn.parent_id is null
		inner join v2020.project p on p.id = nbn.projectid
			and p.projecttype = '1' and p.trackingmethod = '3' and p.deleted is false and p.parent_id is null
			and (p.operatingenddate is null or p.operatingenddate >= dateprovided)
		inner join lsa.tlsa_CohortDates cd on cd.Cohort = 20
			and svc.dateprovided between cd.CohortStart and cd.CohortEnd
		where svc.record_type = '200' and svc.deleted is false and svc.parent_id is null
		group by svc.enrollmentid
		) bn on cast(bn.enrollmentid as varchar) = n.EnrollmentID
	where (hx.exitdate is null and bn.LastBednight < dateadd('d', -90, cd.CohortEnd))
		or (hx.exitdate <> dateadd('d', 1, bn.LastBednight::date))
	group by case when hx.exitdate is null then 60
			else 61 end
		, p.id, cd.ReportID;

/*
	8.21 Get Counts of Enrollments with no Enrollment CoC Record
*/
	insert into lsa.lsa_Calculated
		(Value, Cohort, Universe, HHType
		, Population, SystemPath, ReportRow, ProjectID, ReportID, Step)
	select count (distinct hn.id), 1, 10, 0, 0, -1, 62, cast(p.id as varchar), rpt.ReportID, '8.21'
	from lsa.lsa_Report rpt
	inner join v2020.enrollment hn on hn.entrydate <= rpt.ReportEnd and hn.deleted is false and hn.parent_id is NULL AND rpt.project_group_code=hn.project_group_code
	inner join v2020.project p on p.id = hn.projectid and p.continuumproject = '1' and p.projecttype in ('1','2','3','8','13') and p.deleted is false and p.parent_id is NULL
	inner join v2020.coc pcoc on pcoc.projectid = p.id and pcoc.coccode = rpt.ReportCoC and pcoc.deleted is false and pcoc.parent_id is null
	left outer join v2020.exit hx on hx.enrollmentid = hn.id
		and hx.exitdate >= rpt.ReportStart and hx.deleted is false and hx.parent_id is null
	left outer join v2020.enrollment hoh on hoh.householdid = hn.householdid
		and hoh.relationshiptohoh = '1' and hoh.deleted is false and hoh.parent_id is null
	left outer join v2020.enrollment_coc coc on coc.enrollmentid = hoh.id
		and coc.information_date <= rpt.ReportEnd and coc.deleted is false and coc.parent_id is null
	where coc.coc_code is null
	group by p.id, rpt.ReportID;

	END
$function$
;
