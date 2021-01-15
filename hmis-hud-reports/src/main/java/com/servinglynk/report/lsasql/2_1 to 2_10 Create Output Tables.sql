CREATE OR REPLACE FUNCTION lsa.run_create_output_tables()
RETURNS void
 LANGUAGE plpgsql
AS $function$
BEGIN

/*
LSA FY2019 Sample Code


Name:  2_1 to 2_10 Create Output Tables.sql
Date:  4/7/2020
	   5/14/2020 - lsa."lsa_Report" - allow NULL value in "ReportDate" because it isn't set until the end
				 - lsa."lsa_ProjectCoC" - allow NULL values for "GeographyType" and "ZIP" in the table -- they are still required
						by the HDX
       5/21/2020 -- add column "Step" (varchar(10) not NULL) to lsa."lsa_Calculated".

There are some deliberate differences from data typing and nullability as defined by
the HMIS CSV/LSA specs and the CREATE statements here.

	Columns which may be NULL in the HMIS CSV under some circumstances that do not
	apply to the LSA upload are created as NOT NULL here.  For example, "ProjectType"
	may be NULL in HMIS if "ContinuumProject" = 0, but may not be NULL in the LSA
	because all projects included in the upload must have "ContinuumProject" = 1.

	Columns which may not be NULL in HMIS but are not relevant to the LSA are
	created as NULL here.  For example, "UserID" values are not imported to the HDX
	and may be NULL in the LSA upload.

	Date columns are created with data type varchar to enable date formatting as
	required by HMIS/LSA CSV specs in the INSERT statements.  The only exception is
	"DateDeleted" columns -- they must be NULL for all records and formatting is not
	relevant.

	"ExportID" columns have a string(36) data type for HMIS purposes, but the values
	must match the LSA "ReportID", which is an int column; they are created here as int
	to ensure that the data type, at least, is consistent with LSA requirements.


	2.1 Project.csv / lsa."lsa_Project"
*/
DROP TABLE IF EXISTS lsa.lsa_Project;

--	ProjectType and HousingType may be NULL under some circumstances in the HMIS CSV;
--	none of those circumstances apply to projects included in the LSA upload.

create table lsa.lsa_Project(
	ProjectID varchar(36) not NULL,
	OrganizationID varchar(36) not NULL,
	ProjectName varchar(50) not NULL,
	ProjectCommonName varchar(50),
	OperatingStartDate varchar(10) not NULL,	--HMIS: date
	OperatingEndDate varchar(10),				--HMIS: date
	ContinuumProject int not NULL,
	ProjectType int not NULL,					--HMIS: may be NULL
	HousingType int not NULL,					--HMIS: may be NULL
	ResidentialAffiliation int,
	TrackingMethod int,
	HMISParticipatingProject int not NULL,
	TargetPopulation int,
	PITCount int,
	DateCreated varchar(19) not NULL,			--HMIS: datetime
	DateUpdated varchar(19) not NULL,			--HMIS: datetime
	UserID varchar(36),						--HMIS: not NULL
	DateDeleted timestamp,
	ExportID int not NULL,						--HMIS: string(36)
	CONSTRAINT pk_lsa_Project PRIMARY KEY (ProjectID)
	);

/*
	2.2 Organization.csv / lsa.lsa_Organization
*/
DROP TABLE IF EXISTS lsa.lsa_Organization;

create table lsa.lsa_Organization(
	OrganizationID varchar(36) not NULL,
	OrganizationName varchar(50) not NULL,
	VictimServicesProvider int not NULL,
	OrganizationCommonName varchar(50),
	DateCreated varchar(19) not NULL,			--HMIS: datetime
	DateUpdated varchar(19) not NULL,			--HMIS: datetime
	UserID varchar(36),						    --HMIS: not NULL
	DateDeleted timestamp ,
	ExportID int not NULL,						--HMIS: string(36)
	CONSTRAINT pk_lsa_Organization PRIMARY KEY (OrganizationID)
	);

/*
	2.3 Funder.csv / lsa.lsa_Funder
*/

DROP TABLE IF EXISTS lsa.lsa_Funder;

--	GrantID may not be NULL in HMIS, but it is not relevant for the LSA.

create table lsa.lsa_Funder(
	FunderID varchar(36) not NULL,
	ProjectID varchar(36) not NULL,
	Funder int not NULL,
	OtherFunder varchar(50),
	GrantID varchar(36),						--HMIS: not NULL
	StartDate varchar(10) not NULL,			--HMIS: date
	EndDate varchar(10),						--HMIS: date
	DateCreated varchar(19) not NULL,			--HMIS: datetime
	DateUpdated varchar(19) not NULL,			--HMIS: datetime
	UserID varchar(36),						--HMIS: not NULL
	DateDeleted timestamp ,
	ExportID int not NULL,						--HMIS: string(36)
	CONSTRAINT pk_lsa_Funder PRIMARY KEY (FunderID)
	);

/*
	2.4 ProjectCoC.csv / lsa.lsa_ProjectCoC
*/
DROP TABLE IF EXISTS lsa.lsa_ProjectCoC;

--	ZIP and GeographyType are mandatory for the LSA.
create table lsa.lsa_ProjectCoC(
	ProjectCoCID varchar(36) not NULL,
	ProjectID varchar(36) not NULL,
	CoCCode varchar(6) not NULL,
	Geocode varchar(6) not NULL,
	Address1 varchar(100),
	Address2 varchar(100),
	City varchar(50),
	State varchar(2),
	ZIP varchar(5),
	GeographyType int,
	DateCreated varchar(19) not NULL,			--HMIS: datetime
	DateUpdated varchar(19) not NULL,			--HMIS: datetime
	UserID varchar(36),						--HMIS: not NULL
	DateDeleted timestamp ,
	ExportID int not NULL,						--HMIS: string(36)
	CONSTRAINT pk_lsa_ProjectCoC PRIMARY KEY (ProjectCoCID)
	);

/*
	2.5 Inventory.csv / lsa.lsa_Inventory
*/
DROP TABLE IF EXISTS lsa.lsa_Inventory;

--	xInventory (e.g., CHVetBedInventory, etc.) columns for which the HMIS CSV permits
--	NULL values are are mandatory for the LSA and created here as NOT NULL.
create table lsa.lsa_Inventory(
	InventoryID varchar(36) not NULL,
	ProjectID varchar(36) not NULL,
	CoCCode varchar(6)  not NULL,
	HouseholdType int not NULL,
	Availability int,
	UnitInventory int not NULL,
	BedInventory int not NULL,
	CHVetBedInventory int not NULL,				--HMIS: may be NULL
	YouthVetBedInventory int not NULL,			--HMIS: may be NULL
	VetBedInventory int not NULL,				--HMIS: may be NULL
	CHYouthBedInventory int not NULL,			--HMIS: may be NULL
	YouthBedInventory int not NULL,				--HMIS: may be NULL
	CHBedInventory int not NULL,				--HMIS: may be NULL
	OtherBedInventory int not NULL,				--HMIS: may be NULL
	ESBedType int,
	InventoryStartDate varchar(10) not NULL,	--HMIS: date
	InventoryEndDate varchar(10),				--HMIS: date
	DateCreated varchar(19) not NULL,			--HMIS: datetime
	DateUpdated varchar(19) not NULL,			--HMIS: datetime
	UserID varchar(36),						--HMIS: not NULL
	DateDeleted timestamp ,
	ExportID int not NULL,						--HMIS: string(36)
	CONSTRAINT pk_lsa_Inventory PRIMARY KEY  (InventoryID)
	);

/*
	2.6 LSAReport.csv / lsa.lsa_Report
*/
DROP TABLE IF EXISTS lsa.lsa_Report;

--	The NULL/NOT NULL requirements for this table as it is created here
--	differ from those for the LSAReport.csv file because the values are not
--	populated in a single step. All of the data quality columns
--	(UnduplicatedClient1 through MoveInDate3) must be non-NULL in the upload.
create table lsa.lsa_Report(
	ReportID int not NULL,
	ReportDate timestamp ,
	ReportStart date not NULL,
	ReportEnd date not NULL,
	ReportCoC varchar(6) not NULL,
	SoftwareVendor varchar(50) not NULL,
	SoftwareName varchar(50) not NULL,
	VendorContact varchar(50) not NULL,
	VendorEmail varchar(50) not NULL,
	LSAScope int not NULL,
	UnduplicatedClient1 int,
	UnduplicatedClient3 int,
	UnduplicatedAdult1 int,
	UnduplicatedAdult3 int,
	AdultHoHEntry1 int,
	AdultHoHEntry3 int,
	ClientEntry1 int,
	ClientEntry3 int,
	ClientExit1 int,
	ClientExit3 int,
	Household1 int,
	Household3 int,
	HoHPermToPH1 int,
	HoHPermToPH3 int,
	NoCoC int,
	SSNNotProvided int,
	SSNMissingOrInvalid int,
	ClientSSNNotUnique int,
	DistinctSSNValueNotUnique int,
	DOB1 int,
	DOB3 int,
	Gender1 int,
	Gender3 int,
	Race1 int,
	Race3 int,
	Ethnicity1 int,
	Ethnicity3 int,
	VetStatus1 int,
	VetStatus3 int,
	RelationshipToHoH1 int,
	RelationshipToHoH3 int,
	DisablingCond1 int,
	DisablingCond3 int,
	LivingSituation1 int,
	LivingSituation3 int,
	LengthOfStay1 int,
	LengthOfStay3 int,
	HomelessDate1 int,
	HomelessDate3 int,
	TimesHomeless1 int,
	TimesHomeless3 int,
	MonthsHomeless1 int,
	MonthsHomeless3 int,
	DV1 int,
	DV3 int,
	Destination1 int,
	Destination3 int,
	NotOneHoH1 int,
	NotOneHoH3 int,
	MoveInDate1 int,
	MoveInDate3 int,
	project_group_code varchar
	) ;

/*
	2.7 LSAPerson.csv / lsa.lsa_Person
*/

DROP TABLE IF EXISTS lsa.lsa_Person;

create table lsa.lsa_Person (
	RowTotal int not NULL,
	Gender int not NULL,
	Race int not NULL,
	Ethnicity int not NULL,
	VetStatus int not NULL,
	DisabilityStatus int not NULL,
	CHTime int not NULL,
	CHTimeStatus int not NULL,
	DVStatus int not NULL,
	ESTAgeMin int not NULL,
	ESTAgeMax int not NULL,
	HHTypeEST int not NULL,
	HoHEST int not NULL,
	AdultEST int not NULL,
	HHChronicEST int not NULL,
	HHVetEST int not NULL,
	HHDisabilityEST int not NULL,
	HHFleeingDVEST int not NULL,
	HHAdultAgeAOEST int not NULL,
	HHAdultAgeACEST int not NULL,
	HHParentEST int not NULL,
	AC3PlusEST int not NULL,
	AHAREST int not NULL,
	AHARHoHEST int not NULL,
	RRHAgeMin int not NULL,
	RRHAgeMax int not NULL,
	HHTypeRRH int not NULL,
	HoHRRH int not NULL,
	AdultRRH int not NULL,
	HHChronicRRH int not NULL,
	HHVetRRH int not NULL,
	HHDisabilityRRH int not NULL,
	HHFleeingDVRRH int not NULL,
	HHAdultAgeAORRH int not NULL,
	HHAdultAgeACRRH int not NULL,
	HHParentRRH int not NULL,
	AC3PlusRRH int not NULL,
	AHARRRH int not NULL,
	AHARHoHRRH int not NULL,
	PSHAgeMin int not NULL,
	PSHAgeMax int not NULL,
	HHTypePSH int not NULL,
	HoHPSH int not NULL,
	AdultPSH int not NULL,
	HHChronicPSH int not NULL,
	HHVetPSH int not NULL,
	HHDisabilityPSH int not NULL,
	HHFleeingDVPSH int not NULL,
	HHAdultAgeAOPSH int not NULL,
	HHAdultAgeACPSH int not NULL,
	HHParentPSH int not NULL,
	AC3PlusPSH int not NULL,
	AHARPSH int not NULL,
	AHARHoHPSH int not NULL,
	ReportID int not NULL
	);

/*
	2.8 LSAHousehold.csv / lsa.lsa_Household
*/
DROP TABLE IF EXISTS lsa.lsa_Household;

create table lsa.lsa_Household(
	RowTotal int not NULL,
	Stat int not NULL,
	ReturnTime int not NULL,
	HHType int not NULL,
	HHChronic int not NULL,
	HHVet int not NULL,
	HHDisability int not NULL,
	HHFleeingDV int not NULL,
	HoHRace int not NULL,
	HoHEthnicity int not NULL,
	HHAdult int not NULL,
	HHChild int not NULL,
	HHNoDOB int not NULL,
	HHAdultAge int not NULL,
	HHParent int not NULL,
	ESTStatus int not NULL,
	ESTGeography int not NULL,
	ESTLivingSit int not NULL,
	ESTDestination int not NULL,
	ESTChronic int not NULL,
	ESTVet int not NULL,
	ESTDisability int not NULL,
	ESTFleeingDV int not NULL,
	ESTAC3Plus int not NULL,
	ESTAdultAge int not NULL,
	ESTParent int not NULL,
	RRHStatus int not NULL,
	RRHMoveIn int not NULL,
	RRHGeography int not NULL,
	RRHLivingSit int not NULL,
	RRHDestination int not NULL,
	RRHPreMoveInDays int not NULL,
	RRHChronic int not NULL,
	RRHVet int not NULL,
	RRHDisability int not NULL,
	RRHFleeingDV int not NULL,
	RRHAC3Plus int not NULL,
	RRHAdultAge int not NULL,
	RRHParent int not NULL,
	PSHStatus int not NULL,
	PSHMoveIn int not NULL,
	PSHGeography int not NULL,
	PSHLivingSit int not NULL,
	PSHDestination int not NULL,
	PSHHousedDays int not NULL,
	PSHChronic int not NULL,
	PSHVet int not NULL,
	PSHDisability int not NULL,
	PSHFleeingDV int not NULL,
	PSHAC3Plus int not NULL,
	PSHAdultAge int not NULL,
	PSHParent int not NULL,
	ESDays int not NULL,
	THDays int not NULL,
	ESTDays int not NULL,
	RRHPSHPreMoveInDays int not NULL,
	RRHHousedDays int not NULL,
	SystemDaysNotPSHHoused int not NULL,
	SystemHomelessDays int not NULL,
	Other3917Days int not NULL,
	TotalHomelessDays int not NULL,
	SystemPath int not NULL,
	ESTAHAR int not NULL,
	RRHAHAR int not NULL,
	PSHAHAR int not NULL,
	ReportID int not NULL
	);

/*
	2.9 LSAExit.csv / lsa.lsa_Exit
*/
DROP TABLE IF EXISTS lsa.lsa_Exit;

create table lsa.lsa_Exit(
	RowTotal int not NULL,
	Cohort int not NULL,
	Stat int not NULL,
	ExitFrom int not NULL,
	ExitTo int not NULL,
	ReturnTime int not NULL,
	HHType int not NULL,
	HHVet int not NULL,
	HHDisability int not NULL,
	HHFleeingDV int not NULL,
	HoHRace int not NULL,
	HoHEthnicity int not NULL,
	HHAdultAge int not NULL,
	HHParent int not NULL,
	AC3Plus int not NULL,
	SystemPath int not NULL,
	ReportID int not NULL
	);

/*
	2.10 LSACalculated.csv / lsa.lsa_Calculated
*/

DROP TABLE IF EXISTS lsa.lsa_Calculated;

create table lsa.lsa_Calculated(
	Value int not NULL,
	Cohort int not NULL,
	Universe int not NULL,
	HHType int not NULL,
	Population int not NULL,
	SystemPath int not NULL,
	ProjectID varchar(64),
	ReportRow int not NULL,
	ReportID int not NULL,
	Step varchar(10) not NULL
	);


	END
$function$
;
