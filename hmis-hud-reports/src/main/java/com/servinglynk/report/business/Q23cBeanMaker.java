package com.servinglynk.report.business;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.servinglynk.report.bean.Q23cDataBean;
import com.servinglynk.report.bean.ReportData;
import com.servinglynk.report.model.Q22BeanModel;

public class Q23cBeanMaker extends BaseBeanMaker {
	public static int numOfDays = 90;
	public static List<Q23cDataBean> getQ23cList(ReportData data){
		Q23cDataBean q23cDataBean = new Q23cDataBean();
	
		String query = 		  
				" select distinct(e.dedup_client_id ),p.projecttype,p.trackingmethod,p.operatingstartdate,ext.exitdate,e.entrydate,mid.moveindate,ext.destination  from %s.enrollment e join %s.project p  on (e.projectid = p.id  %p ) "+
						"  left outer join %s.exit ext on ( e.id = ext.enrollmentid and ext.exitdate >= :startDate  and ext.exitdate <= :endDate) "+
						" left outer join %s.moveindate mid on (e.id = mid.enrollmentid) "+
						" join %s.enrollment e1 on (e.householdid = e1.householdid and e1.relationshipToHoH='1')  ";
		
		
		try {
			if(data.isLiveMode()) {
				List<Q22BeanModel> allData = getQ22BeanLengthOfStayForExit(data, query, null,true,true);
				List<Q22BeanModel> withoutChildren = getQ22BeanLengthOfStayForExit(data, query, data.getProjectsHHWithOutChildren(),false,true);
				List<Q22BeanModel> withChildAndAdults = getQ22BeanLengthOfStayForExit(data, query, data.getProjectsHHWithOneAdultChild(),false,true);
				List<Q22BeanModel> withChildren = getQ22BeanLengthOfStayForExit(data, query, data.getProjectsHHWithChildren(),false,true);
				List<Q22BeanModel> unknown = getQ22BeanLengthOfStayForExit(data, query, data.getProjectsUnknownHouseHold(),false,true);
				//3
				int allTotal3 = getDestination(allData, "26");
				int allTotal4 = getDestination(allData, "11");
				int allTotal5 = getDestination(allData, "21");
				int allTotal6 = getDestination(allData, "10");
				int allTotal7 = getDestination(allData, "19");
				int allTotal8 = getDestination(allData, "28");
				int allTotal9 = getDestination(allData, "20");
				int allTotal10 = getDestination(allData, "3");
				int allTotal11 = getDestination(allData, "22");
				int allTotal12 = getDestination(allData, "23");
				int allTotal13 = getDestination(allData, "31");
				int allTotal33 = getDestination(allData, "33");
				int allTotal34 = getDestination(allData, "34");
				
				int withoutChildren3 = getDestination(withoutChildren, "26");
				int withoutChildren4 = getDestination(withoutChildren, "11");
				int withoutChildren5 = getDestination(withoutChildren, "21");
				int withoutChildren6 = getDestination(withoutChildren, "10");
				int withoutChildren7 = getDestination(withoutChildren, "19");
				int withoutChildren8 = getDestination(withoutChildren, "28");
				int withoutChildren9 = getDestination(withoutChildren, "20");
				int withoutChildren10 = getDestination(withoutChildren, "3");
				int withoutChildren11 = getDestination(withoutChildren, "22");
				int withoutChildren12 = getDestination(withoutChildren, "23");
				int withoutChildren13 = getDestination(withoutChildren, "31");
				int withoutChildren33 = getDestination(withoutChildren, "33");
				int withoutChildren34 = getDestination(withoutChildren, "34");
				
				int withChildAndAdults3 = getDestination(withChildAndAdults, "26");
				int withChildAndAdults4 = getDestination(withChildAndAdults, "11");
				int withChildAndAdults5 = getDestination(withChildAndAdults, "21");
				int withChildAndAdults6 = getDestination(withChildAndAdults, "10");
				int withChildAndAdults7 = getDestination(withChildAndAdults, "19");
				int withChildAndAdults8 = getDestination(withChildAndAdults, "28");
				int withChildAndAdults9 = getDestination(withChildAndAdults, "20");
				int withChildAndAdults10 = getDestination(withChildAndAdults, "3");
				int withChildAndAdults11 = getDestination(withChildAndAdults, "22");
				int withChildAndAdults12 = getDestination(withChildAndAdults, "23");
				int withChildAndAdults13 = getDestination(withChildAndAdults, "31");
				int withChildAndAdults33 = getDestination(withChildAndAdults, "33");
				int withChildAndAdults34 = getDestination(withChildAndAdults, "34");
				
				int withChildren3 = getDestination(withChildren, "26");
				int withChildren4 = getDestination(withChildren, "11");
				int withChildren5 = getDestination(withChildren, "21");
				int withChildren6 = getDestination(withChildren, "10");
				int withChildren7 = getDestination(withChildren, "19");
				int withChildren8 = getDestination(withChildren, "28");
				int withChildren9 = getDestination(withChildren, "20");
				int withChildren10 = getDestination(withChildren, "3");
				int withChildren11 = getDestination(withChildren, "22");
				int withChildren12 = getDestination(withChildren, "23");
				int withChildren13 = getDestination(withChildren, "31");
				int withChildren33 = getDestination(withChildren, "33");
				int withChildren34 = getDestination(withChildren, "34");
				
				int unknown3 = getDestination(unknown, "26");
				int unknown4 = getDestination(unknown, "11");
				int unknown5 = getDestination(unknown, "21");
				int unknown6 = getDestination(unknown, "10");
				int unknown7 = getDestination(unknown, "19");
				int unknown8 = getDestination(unknown, "28");
				int unknown9 = getDestination(unknown, "20");
				int unknown10 = getDestination(unknown, "3");
				int unknown11 = getDestination(unknown, "22");
				int unknown12 = getDestination(unknown, "23");
				int unknown13 = getDestination(unknown, "31");
				int unknown33 = getDestination(unknown, "33");
				int unknown34 = getDestination(unknown, "34");
				
				//3
				q23cDataBean.setQ23CA01MovedFromOneHOPWATotal(BigInteger.valueOf(allTotal3));
				q23cDataBean.setQ23CA01MovedFromOneHOPWAWithoutChild( BigInteger.valueOf(withoutChildren3) );
				q23cDataBean.setQ23CA01MovedFromOneHOPWAWithChildAndAdults(BigInteger.valueOf(withChildAndAdults3));
				q23cDataBean.setQ23CA01MovedFromOneHOPWAWithOnlyChild(BigInteger.valueOf(withChildren3) );
				q23cDataBean.setQ23CA01MovedFromOneHOPWAUnknownHouseHold(BigInteger.valueOf(unknown3));
				//4
				q23cDataBean.setQ23CA02OwnedByClientNoOngoingSubsidyTotal(BigInteger.valueOf(allTotal4));
				q23cDataBean.setQ23CA02OwnedByClientNoOngoingSubsidyWithoutChild(BigInteger.valueOf(withoutChildren4) );
				q23cDataBean.setQ23CA02OwnedByClientNoOngoingSubsidyWithChildAndAdults(BigInteger.valueOf(withChildAndAdults4) );
				q23cDataBean.setQ23CA02OwnedByClientNoOngoingSubsidyWithOnlyChild(BigInteger.valueOf(withChildren4) );
				q23cDataBean.setQ23CA02OwnedByClientNoOngoingSubsidyUnknownHouseHold(BigInteger.valueOf(unknown4) );
				//5
				q23cDataBean.setQ23CA03OwnedByClientWithOngoingSubsidyTotal(BigInteger.valueOf(allTotal5));
				q23cDataBean.setQ23CA03OwnedByClientWithOngoingSubsidyWithoutChild(BigInteger.valueOf(withoutChildren5));
				q23cDataBean.setQ23CA03OwnedByClientWithOngoingSubsidyWithChildAndAdults(BigInteger.valueOf(withChildAndAdults5));
				q23cDataBean.setQ23CA03OwnedByClientWithOngoingSubsidyWithOnlyChild(BigInteger.valueOf(withChildren5));
				q23cDataBean.setQ23CA03OwnedByClientWithOngoingSubsidyUnknownHouseHold(BigInteger.valueOf(unknown5));
				//6
				q23cDataBean.setQ23CA04RentalByClientNoOngoingSubsidyTotal(BigInteger.valueOf(allTotal6));
				q23cDataBean.setQ23CA04RentalByClientNoOngoingSubsidyWithoutChild(BigInteger.valueOf(withoutChildren6));
				q23cDataBean.setQ23CA04RentalByClientNoOngoingSubsidyWithChildAndAdults(BigInteger.valueOf(withChildAndAdults6));
				q23cDataBean.setQ23CA04RentalByClientNoOngoingSubsidyWithOnlyChild(BigInteger.valueOf(withChildren6));
				q23cDataBean.setQ23CA04RentalByClientNoOngoingSubsidyUnknownHouseHold(BigInteger.valueOf(unknown6));
				//7
				q23cDataBean.setQ23CA05RentalByClientVASHSubsidyTotal(BigInteger.valueOf(allTotal7));
				q23cDataBean.setQ23CA05RentalByClientVASHSubsidyWithoutChild(BigInteger.valueOf(withoutChildren7));
				q23cDataBean.setQ23CA05RentalByClientVASHSubsidyWithChildAndAdults(BigInteger.valueOf(withChildAndAdults7));
				q23cDataBean.setQ23CA05RentalByClientVASHSubsidyWithOnlyChild(BigInteger.valueOf(withChildren7));
				q23cDataBean.setQ23CA05RentalByClientVASHSubsidyUnknownHouseHold(BigInteger.valueOf(unknown7));
				//8
				q23cDataBean.setQ23CA06RentalByClientwithGPDTIPHousingTotal(BigInteger.valueOf(allTotal8));
				q23cDataBean.setQ23CA06RentalByClientwithGPDTIPHousingWithoutChild(BigInteger.valueOf(withoutChildren8));
				q23cDataBean.setQ23CA06RentalByClientwithGPDTIPHousingWithChildAndAdults(BigInteger.valueOf(withChildAndAdults8));
				q23cDataBean.setQ23CA06RentalByClientwithGPDTIPHousingWithOnlyChild(BigInteger.valueOf(withChildren8));
				q23cDataBean.setQ23CA06RentalByClientwithGPDTIPHousingUnknownHouseHold(BigInteger.valueOf(unknown8));
				//9
				q23cDataBean.setQ23CA07RentalByClientOtherOngoingSubsidyTotal(BigInteger.valueOf(allTotal9));
				q23cDataBean.setQ23CA07RentalByClientOtherOngoingSubsidyWithoutChild(BigInteger.valueOf(withoutChildren9));
				q23cDataBean.setQ23CA07RentalByClientOtherOngoingSubsidyWithChildAndAdults(BigInteger.valueOf(withChildAndAdults9));
				q23cDataBean.setQ23CA07RentalByClientOtherOngoingSubsidyWithOnlyChild(BigInteger.valueOf(withChildren9));
				q23cDataBean.setQ23CA07RentalByClientOtherOngoingSubsidyUnknownHouseHold(BigInteger.valueOf(unknown9));
				//10
				q23cDataBean.setQ23CA08PermanentHousingForHomelessTotal(BigInteger.valueOf(allTotal10));
				q23cDataBean.setQ23CA08PermanentHousingForHomelessWithoutChild(BigInteger.valueOf(withoutChildren10));
				q23cDataBean.setQ23CA08PermanentHousingForHomelessWithChildAndAdults(BigInteger.valueOf(withChildAndAdults10));
				q23cDataBean.setQ23CA08PermanentHousingForHomelessWithOnlyChild(BigInteger.valueOf(withChildren10));
				q23cDataBean.setQ23CA08PermanentHousingForHomelessUnknownHouseHold(BigInteger.valueOf(unknown10));
				//11
				q23cDataBean.setQ23CA09StayingOrLivingWithFamilyPermanentTotal(BigInteger.valueOf(allTotal11));
				q23cDataBean.setQ23CA09StayingOrLivingWithFamilyPermanentWithoutChild((BigInteger.valueOf(withoutChildren11)));
				q23cDataBean.setQ23CA09StayingOrLivingWithFamilyPermanentWithChildAndAdults(BigInteger.valueOf(withChildAndAdults11));
				q23cDataBean.setQ23CA09StayingOrLivingWithFamilyPermanentWithOnlyChild(BigInteger.valueOf(withChildren11));
				q23cDataBean.setQ23CA09StayingOrLivingWithFamilyPermanentUnknownHouseHold(BigInteger.valueOf(unknown11));
				//12
				q23cDataBean.setQ23CA10StayingOrLivingWithFriendsPermanentTotal(BigInteger.valueOf(allTotal12));
				q23cDataBean.setQ23CA10StayingOrLivingWithFriendsPermanentWithoutChild(BigInteger.valueOf(withoutChildren12));
				q23cDataBean.setQ23CA10StayingOrLivingWithFriendsPermanentWithChildAndAdults(BigInteger.valueOf(withChildAndAdults12));
				q23cDataBean.setQ23CA10StayingOrLivingWithFriendsPermanentWithOnlyChild(BigInteger.valueOf(withChildren12));
				q23cDataBean.setQ23CA10StayingOrLivingWithFriendsPermanentUnknownHouseHold(BigInteger.valueOf(unknown12));
				//33
				q23cDataBean.setQ23C13ClientByHVCVoucherTotal(BigInteger.valueOf(allTotal33));
				q23cDataBean.setQ23C13ClientByHVCVoucherWithoutChild(BigInteger.valueOf(withoutChildren33));
				q23cDataBean.setQ23C13ClientByHVCVoucherWithOnlyChild(BigInteger.valueOf(withChildren33));
				q23cDataBean.setQ23C13ClientByHVCVoucherWithChildAndAdults(BigInteger.valueOf(withChildAndAdults33));
				q23cDataBean.setQ23C13ClientByHVCVoucherUnknownHouseHold(BigInteger.valueOf(unknown33));
				//34
				q23cDataBean.setQ23C13RentalByClientPublicHousingTotal(BigInteger.valueOf(allTotal34));
				q23cDataBean.setQ23C13RentalByClientPublicHousingWithoutChild(BigInteger.valueOf(withoutChildren34));
				q23cDataBean.setQ23C13RentalByClientPublicHousingWithOnlyChild(BigInteger.valueOf(withChildren34));
				q23cDataBean.setQ23C13RentalByClientPublicHousingWithChildAndAdults(BigInteger.valueOf(withChildAndAdults34));
				q23cDataBean.setQ23C13RentalByClientPublicHousingUnknownHouseHold(BigInteger.valueOf(unknown33));
				
				int subTotal1AllData = allTotal3 + allTotal4+ allTotal5+ allTotal6+ allTotal7 + allTotal8 + allTotal9 + allTotal10 + allTotal11 + allTotal12 + allTotal13 + allTotal33 + allTotal34 ;
				int subTotal1WithoutChild = withoutChildren3 + withoutChildren4+ withoutChildren5+ withoutChildren6+ withoutChildren7 + withoutChildren8 + withoutChildren9 + withoutChildren10 + withoutChildren11 + withoutChildren12 + withoutChildren13 + withoutChildren33 + withoutChildren34;
				int subTotal1WithChildAndAdults = withChildAndAdults3 + withChildAndAdults4+ withChildAndAdults5+ withChildAndAdults6+ withChildAndAdults7 + withChildAndAdults8 + withChildAndAdults9 + withChildAndAdults10 + withChildAndAdults11 + withChildAndAdults12 + withChildAndAdults13 + withChildAndAdults33 + withChildAndAdults34;
				int subTotal1WithChildren = withChildren3 + withChildren4+ withChildren5+ withChildren6+ withChildren7 + withChildren8 + withChildren9 + withChildren10 + withChildren11 + withChildren12 + withChildren13 + withChildren33 + withChildren34;
				int subTotal1Unknown = unknown3 + unknown4+ unknown5+ unknown6+ unknown7 + unknown8 + unknown9 + unknown10 + unknown11 + unknown12 + unknown13 + unknown33 + unknown34;
				
				
				//Sandeep TODO :  Missing row for # 13 Rental by client, with RRH or equivalent subsidy
				//14
				q23cDataBean.setQ23CAzTotTotal(BigInteger.valueOf(subTotal1AllData));
				q23cDataBean.setQ23CAzTotWithoutChild(BigInteger.valueOf(subTotal1WithoutChild));
				q23cDataBean.setQ23CAzTotWithChildAndAdults(BigInteger.valueOf(subTotal1WithChildAndAdults));
				q23cDataBean.setQ23CAzTotWithOnlyChild(BigInteger.valueOf(subTotal1WithChildren));
				q23cDataBean.setQ23CAzTotUnknownHouseHold(BigInteger.valueOf(subTotal1Unknown));
				// Permanent Housing sub total above
				
//				q23cDataBean.setQ23CBTemporaryDestinationsTotal(BigInteger.valueOf(0));
//				q23cDataBean.setQ23CBTemporaryDestinationsWithoutChild(BigInteger.valueOf(0));
//				q23cDataBean.setQ23CBTemporaryDestinationsWithChildAndAdults(BigInteger.valueOf(0));
//				q23cDataBean.setQ23CBTemporaryDestinationsWithOnlyChild(BigInteger.valueOf(0));
//				q23cDataBean.setQ23CBTemporaryDestinationsUnknownHouseHold(BigInteger.valueOf(0));

				int allTotal16 = getDestination(allData, "1");
				int allTotal17 = getDestination(allData, "27");
				int allTotal18 = getDestination(allData, "2");
				int allTotal19 = getDestination(allData, "12");
				int allTotal20 = getDestination(allData, "13");
				int allTotal21 = getDestination(allData, "16");
				int allTotal22 = getDestination(allData, "18");
				int allTotal23 = getDestination(allData, "14");
				int allTotal32 = getDestination(allData, "32");
				
				int withoutChildren16 = getDestination(withoutChildren, "1");
				int withoutChildren17 = getDestination(withoutChildren, "27");
				int withoutChildren18 = getDestination(withoutChildren, "2");
				int withoutChildren19 = getDestination(withoutChildren, "12");
				int withoutChildren20 = getDestination(withoutChildren, "13");
				int withoutChildren21 = getDestination(withoutChildren, "16");
				int withoutChildren22 = getDestination(withoutChildren, "18");
				int withoutChildren23 = getDestination(withoutChildren, "14");
				int withoutChildren32 = getDestination(withoutChildren, "32");
				
				
				int withChildAndAdult16 = getDestination(withChildAndAdults, "1");
				int withChildAndAdult17 = getDestination(withChildAndAdults, "27");
				int withChildAndAdult18 = getDestination(withChildAndAdults, "2");
				int withChildAndAdult19 = getDestination(withChildAndAdults, "12");
				int withChildAndAdult20 = getDestination(withChildAndAdults, "13");
				int withChildAndAdult21 = getDestination(withChildAndAdults, "16");
				int withChildAndAdult22 = getDestination(withChildAndAdults, "18");
				int withChildAndAdult23 = getDestination(withChildAndAdults, "14");
				int withChildAndAdult32 = getDestination(withChildAndAdults, "32");
				
				int withChildren16 = getDestination(withChildren, "1");
				int withChildren17 = getDestination(withChildren, "27");
				int withChildren18 = getDestination(withChildren, "2");
				int withChildren19 = getDestination(withChildren, "12");
				int withChildren20 = getDestination(withChildren, "13");
				int withChildren21 = getDestination(withChildren, "16");
				int withChildren22 = getDestination(withChildren, "18");
				int withChildren23 = getDestination(withChildren, "14");
				int withChildren32 = getDestination(withChildren, "32");
				
				int unknown16 = getDestination(unknown, "1");
				int unknown17 = getDestination(unknown, "27");
				int unknown18 = getDestination(unknown, "2");
				int unknown19 = getDestination(unknown, "12");
				int unknown20 = getDestination(unknown, "13");
				int unknown21 = getDestination(unknown, "16");
				int unknown22 = getDestination(unknown, "18");
				int unknown23 = getDestination(unknown, "14");
				int unknown32 = getDestination(unknown, "32");
				
				int subTotal2AllData =  allTotal16+ allTotal17 + allTotal18 + allTotal19 + allTotal20 + allTotal21 + allTotal22 + allTotal23 + allTotal32;
				int subTotal2WithoutChild =  withoutChildren16+ withoutChildren17 + withoutChildren18 + withoutChildren19 + withoutChildren20 + withoutChildren21 + withoutChildren22 + withoutChildren23 + withoutChildren32;
				int subTotal2WithChildAndAdults = withChildAndAdult16 + withChildAndAdult17 + withChildAndAdult18 + withChildAndAdult19 + withChildAndAdult20 + withChildAndAdult21 + withChildAndAdult22 + withChildAndAdult23 + withChildAndAdult32;
				int subTotal2WithChildren =  withChildren16+ withChildren17 + withChildren18 + withChildren19 + withChildren20 + withChildren21 + withChildren22 + withChildren23 + withChildren32;
				int subTotal2Unknown = unknown16+ unknown17 + unknown18 + unknown19 + unknown20 + unknown21 + unknown22 + unknown23 + unknown32;
				
				//16
				q23cDataBean.setQ23CB1EmergencyShelterTotal(BigInteger.valueOf(allTotal16));
				q23cDataBean.setQ23CB1EmergencyShelterWithoutChild(BigInteger.valueOf(withoutChildren16));
				q23cDataBean.setQ23CB1EmergencyShelterWithChildAndAdults(BigInteger.valueOf(withChildAndAdult16));
				q23cDataBean.setQ23CB1EmergencyShelterWithOnlyChild(BigInteger.valueOf(withChildren16));
				q23cDataBean.setQ23CB1EmergencyShelterUnknownHouseHold(BigInteger.valueOf(unknown16));
				//17
				q23cDataBean.setQ23CB2MovedFromOneHOPWATotal(BigInteger.valueOf(allTotal17));
				q23cDataBean.setQ23CB2MovedFromOneHOPWAWithoutChild(BigInteger.valueOf(withoutChildren17));
				q23cDataBean.setQ23CB2MovedFromOneHOPWAWithChildAndAdults(BigInteger.valueOf(withChildAndAdult17));
				q23cDataBean.setQ23CB2MovedFromOneHOPWAWithOnlyChild(BigInteger.valueOf(withChildren17));
				q23cDataBean.setQ23CB2MovedFromOneHOPWAUnknownHouseHold(BigInteger.valueOf(unknown17));
				//18
				q23cDataBean.setQ23CB3TransitionalHousingForHomeLessTotal(BigInteger.valueOf(allTotal18));
				q23cDataBean.setQ23CB3TransitionalHousingForHomeLessWithoutChild(BigInteger.valueOf(withoutChildren18));
				q23cDataBean.setQ23CB3TransitionalHousingForHomeLessWithChildAndAdults(BigInteger.valueOf(withChildAndAdult18));
				q23cDataBean.setQ23CB3TransitionalHousingForHomeLessWithOnlyChild(BigInteger.valueOf(withChildren18));
				q23cDataBean.setQ23CB3TransitionalHousingForHomeLessUnknownHouseHold(BigInteger.valueOf(unknown18));
				//19
				q23cDataBean.setQ23CB4StayingWithFamilyTemporaryTenureTotal(BigInteger.valueOf(allTotal19));
				q23cDataBean.setQ23CB4StayingWithFamilyTemporaryTenureWithoutChild(BigInteger.valueOf(withoutChildren19));
				q23cDataBean.setQ23CB4StayingWithFamilyTemporaryTenureWithChildAndAdults(BigInteger.valueOf(withChildAndAdult19));
				q23cDataBean.setQ23CB4StayingWithFamilyTemporaryTenureWithOnlyChild(BigInteger.valueOf(withChildren19));
				q23cDataBean.setQ23CB4StayingWithFamilyTemporaryTenureUnknownHouseHold(BigInteger.valueOf(unknown19));
				//20
				q23cDataBean.setQ23CB5StayingWithFriendsTemporaryTenureTotal(BigInteger.valueOf(allTotal20));
				q23cDataBean.setQ23CB5StayingWithFriendsTemporaryTenureWithoutChild(BigInteger.valueOf(withoutChildren20));
				q23cDataBean.setQ23CB5StayingWithFriendsTemporaryTenureWithChildAndAdults(BigInteger.valueOf(withChildAndAdult20));
				q23cDataBean.setQ23CB5StayingWithFriendsTemporaryTenureWithOnlyChild(BigInteger.valueOf(withChildren20));
				q23cDataBean.setQ23CB5StayingWithFriendsTemporaryTenureUnknownHouseHold(BigInteger.valueOf(unknown20));
				//21
				q23cDataBean.setQ23CB6PlaceNotMeantForHumanHabitationTotal(BigInteger.valueOf(allTotal21));
				q23cDataBean.setQ23CB6PlaceNotMeantForHumanHabitationWithoutChild(BigInteger.valueOf(withoutChildren21));
				q23cDataBean.setQ23CB6PlaceNotMeantForHumanHabitationWithChildAndAdults(BigInteger.valueOf(withChildAndAdult21));
				q23cDataBean.setQ23CB6PlaceNotMeantForHumanHabitationWithOnlyChild(BigInteger.valueOf(withChildren21));
				q23cDataBean.setQ23CB6PlaceNotMeantForHumanHabitationUnknownHouseHold(BigInteger.valueOf(unknown21));
				//22
				q23cDataBean.setQ23CB7SafeHavenTotal(BigInteger.valueOf(allTotal22));
				q23cDataBean.setQ23CB7SafeHavenWithoutChild(BigInteger.valueOf(withoutChildren22));
				q23cDataBean.setQ23CB7SafeHavenWithChildAndAdults(BigInteger.valueOf(withChildAndAdult22));
				q23cDataBean.setQ23CB7SafeHavenWithOnlyChild(BigInteger.valueOf(withChildren22));
				q23cDataBean.setQ23CB7SafeHavenUnknownHouseHold(BigInteger.valueOf(unknown22));
				//23
				q23cDataBean.setQ23CB8HotelOrMotelPaidByClientTotal(BigInteger.valueOf(allTotal23));
				q23cDataBean.setQ23CB8HotelOrMotelPaidByClientWithoutChild(BigInteger.valueOf(withoutChildren23));
				q23cDataBean.setQ23CB8HotelOrMotelPaidByClientWithChildAndAdults(BigInteger.valueOf(withChildAndAdult23));
				q23cDataBean.setQ23CB8HotelOrMotelPaidByClientWithOnlyChild(BigInteger.valueOf(withChildren23));
				q23cDataBean.setQ23CB8HotelOrMotelPaidByClientUnknownHouseHold(BigInteger.valueOf(unknown23));
				//32
				q23cDataBean.setQ23CBHostHomeTotal(BigInteger.valueOf(allTotal32));
				q23cDataBean.setQ23CB8HostHomeWithoutChild(BigInteger.valueOf(withoutChildren32));
				q23cDataBean.setQ23CB8HostHomeWithChildAndAdults(BigInteger.valueOf(withChildAndAdult32));
				q23cDataBean.setQ23CB8HostHomeWithOnlyChild(BigInteger.valueOf(withChildren32));
				q23cDataBean.setQ23CB8HostHomeUnknownHouseHold(BigInteger.valueOf(unknown32));
				
			
				
				//24
				q23cDataBean.setQ23CBzTotTotal(BigInteger.valueOf(subTotal2AllData));
				q23cDataBean.setQ23CBzTotWithoutChild(BigInteger.valueOf(subTotal2WithoutChild));
				q23cDataBean.setQ23CBzTotWithChildAndAdults(BigInteger.valueOf(subTotal2WithChildAndAdults));
				q23cDataBean.setQ23CBzTotWithOnlyChild(BigInteger.valueOf(subTotal2WithChildren));
				q23cDataBean.setQ23CBzTotUnknownHouseHold(BigInteger.valueOf(subTotal2Unknown));
				// Temp Housing sub total above
				
				//Sandeep TODO : need to remove the below  row or it can be empty string.
//				q23cDataBean.setQ23CCInstitutionalSettingsTotal(BigInteger.valueOf(0));
//				q23cDataBean.setQ23CCInstitutionalSettingsWithoutChild(BigInteger.valueOf(0));
//				q23cDataBean.setQ23CCInstitutionalSettingsWithChildAndAdults(BigInteger.valueOf(0));
//				q23cDataBean.setQ23CCInstitutionalSettingsWithOnlyChild(BigInteger.valueOf(0));
//				q23cDataBean.setQ23CCInstitutionalSettingsUnknownHouseHold(BigInteger.valueOf(0));
				
				
				int allTotal26 = getDestination(allData, "15");
				int allTotal27 = getDestination(allData, "4");
				int allTotal28 = getDestination(allData, "5");
				int allTotal29 = getDestination(allData, "6");
				int allTotal30 = getDestination(allData, "7");
				int allTotal31 = getDestination(allData, "25");
				
				int withoutChildren26 = getDestination(withoutChildren, "15");
				int withoutChildren27 = getDestination(withoutChildren, "4");
				int withoutChildren28 = getDestination(withoutChildren, "5");
				int withoutChildren29 = getDestination(withoutChildren, "6");
				int withoutChildren30 = getDestination(withoutChildren, "7");
				int withoutChildren31 = getDestination(withoutChildren, "25");
				
				int withChildAndAdult26 = getDestination(withChildAndAdults, "15");
				int withChildAndAdult27 = getDestination(withChildAndAdults, "4");
				int withChildAndAdult28 = getDestination(withChildAndAdults, "5");
				int withChildAndAdult29 = getDestination(withChildAndAdults, "6");
				int withChildAndAdult30 = getDestination(withChildAndAdults, "7");
				int withChildAndAdult31 = getDestination(withChildAndAdults, "25");
				
				int withChildren26 = getDestination(withChildren, "15");
				int withChildren27 = getDestination(withChildren, "4");
				int withChildren28 = getDestination(withChildren, "5");
				int withChildren29 = getDestination(withChildren, "6");
				int withChildren30 = getDestination(withChildren, "7");
				int withChildren31 = getDestination(withChildren, "25");
				
				int unknown26 = getDestination(unknown, "15");
				int unknown27 = getDestination(unknown, "4");
				int unknown28 = getDestination(unknown, "5");
				int unknown29 = getDestination(unknown, "6");
				int unknown30 = getDestination(unknown, "7");
				int unknown31 = getDestination(unknown, "25");
				
				int subTotal3AllData =  allTotal26+ allTotal27 + allTotal28 + allTotal29 + allTotal30 + allTotal31 ;
				int subTotal3WithoutChild =  withoutChildren26+ withoutChildren27 + withoutChildren28 + withoutChildren29 + withoutChildren30 + withoutChildren31 ;
				int subTotal3WithChildAndAdults = withChildAndAdult26 + withChildAndAdult27 + withChildAndAdult28 + withChildAndAdult29 + withChildAndAdult30 + withChildAndAdult31 ;
				int subTotal3WithChildren =  withChildren26+ withChildren27 + withChildren28 + withChildren29 + withChildren30 + withChildren31 ;
				int subTotal3Unknown = unknown26+ unknown27 + unknown28 + unknown29 + unknown30 + unknown31 ;
				
				//26
				q23cDataBean.setQ23CC1FosterCareHomeOrGroupFosterCareTotal(BigInteger.valueOf(allTotal26));
				q23cDataBean.setQ23CC1FosterCareHomeOrGroupFosterCareWithoutChild(BigInteger.valueOf(withoutChildren26));
				q23cDataBean.setQ23CC1FosterCareHomeOrGroupFosterCareWithChildAndAdults(BigInteger.valueOf(withChildAndAdult26));
				q23cDataBean.setQ23CC1FosterCareHomeOrGroupFosterCareWithOnlyChild(BigInteger.valueOf(withChildren26));
				q23cDataBean.setQ23CC1FosterCareHomeOrGroupFosterCareUnknownHouseHold(BigInteger.valueOf(unknown26));
				//27
				q23cDataBean.setQ23CC2PsychiatricHospitalOrOtherTotal(BigInteger.valueOf(allTotal27));
				q23cDataBean.setQ23CC2PsychiatricHospitalOrOtherWithoutChild(BigInteger.valueOf(withoutChildren27));
				q23cDataBean.setQ23CC2PsychiatricHospitalOrOtherWithChildAndAdults(BigInteger.valueOf(withChildAndAdult27));
				q23cDataBean.setQ23CC2PsychiatricHospitalOrOtherWithOnlyChild(BigInteger.valueOf(withChildren27));
				q23cDataBean.setQ23CC2PsychiatricHospitalOrOtherUnknownHouseHold(BigInteger.valueOf(unknown27));
				//28
				q23cDataBean.setQ23CC3SubstanceAbuseTreatmentFacilityTotal(BigInteger.valueOf(allTotal28));
				q23cDataBean.setQ23CC3SubstanceAbuseTreatmentFacilityWithoutChild(BigInteger.valueOf(withoutChildren28));
				q23cDataBean.setQ23CC3SubstanceAbuseTreatmentFacilityWithChildAndAdults(BigInteger.valueOf(withChildAndAdult28));
				q23cDataBean.setQ23CC3SubstanceAbuseTreatmentFacilityWithOnlyChild(BigInteger.valueOf(withChildren28));
				q23cDataBean.setQ23CC3SubstanceAbuseTreatmentFacilityUnknownHouseHold(BigInteger.valueOf(unknown28));
				//29
				q23cDataBean.setQ23CC4HospitalOrOtherTotal(BigInteger.valueOf(allTotal29));
				q23cDataBean.setQ23CC4HospitalOrOtherWithoutChild(BigInteger.valueOf(withoutChildren29));
				q23cDataBean.setQ23CC4HospitalOrOtherWithChildAndAdults(BigInteger.valueOf(withChildAndAdult29));
				q23cDataBean.setQ23CC4HospitalOrOtherWithOnlyChild(BigInteger.valueOf(withChildren29));
				q23cDataBean.setQ23CC4HospitalOrOtherUnknownHouseHold(BigInteger.valueOf(unknown29));
				//30
				q23cDataBean.setQ23CC5JailPrisonOrJuvenileDetentionTotal(BigInteger.valueOf(allTotal30));
				q23cDataBean.setQ23CC5JailPrisonOrJuvenileDetentionWithoutChild(BigInteger.valueOf(withoutChildren30));
				q23cDataBean.setQ23CC5JailPrisonOrJuvenileDetentionWithChildAndAdults(BigInteger.valueOf(withChildAndAdult30));
				q23cDataBean.setQ23CC5JailPrisonOrJuvenileDetentionWithOnlyChild(BigInteger.valueOf(withChildren30));
				q23cDataBean.setQ23CC5JailPrisonOrJuvenileDetentionUnknownHouseHold(BigInteger.valueOf(unknown30));
				//31
				q23cDataBean.setQ23CC6LongTermCareFacilityOrNursingHomeTotal(BigInteger.valueOf(allTotal31));
				q23cDataBean.setQ23CC6LongTermCareFacilityOrNursingHomeWithoutChild(BigInteger.valueOf(withoutChildren31));
				q23cDataBean.setQ23CC6LongTermCareFacilityOrNursingHomeWithChildAndAdults(BigInteger.valueOf(withChildAndAdult31));
				q23cDataBean.setQ23CC6LongTermCareFacilityOrNursingHomeWithOnlyChild(BigInteger.valueOf(withChildren31));
				q23cDataBean.setQ23CC6LongTermCareFacilityOrNursingHomeUnknownHouseHold(BigInteger.valueOf(unknown31));
				
				//32
				q23cDataBean.setQ23CCzTotTotal(BigInteger.valueOf(subTotal3AllData));
				q23cDataBean.setQ23CCzTotWithoutChild(BigInteger.valueOf(subTotal3WithoutChild));
				q23cDataBean.setQ23CCzTotWithChildAndAdults(BigInteger.valueOf(subTotal3WithChildAndAdults));
				q23cDataBean.setQ23CCzTotWithOnlyChild(BigInteger.valueOf(subTotal3WithChildren));
				q23cDataBean.setQ23CCzTotUnknownHouseHold(BigInteger.valueOf(subTotal3Unknown));
				
				// 33  No data needs to be populdated for 33...
//				q23cDataBean.setQ23CDOtherDestinationsTotal(BigInteger.valueOf(0));
//				q23cDataBean.setQ23CDOtherDestinationsWithoutChild(BigInteger.valueOf(0));
//				q23cDataBean.setQ23CDOtherDestinationsWithChildAndAdults(BigInteger.valueOf(0));
//				q23cDataBean.setQ23CDOtherDestinationsWithOnlyChild(BigInteger.valueOf(0));
//				q23cDataBean.setQ23CDOtherDestinationsUnknownHouseHold(BigInteger.valueOf(0));
//				
				
				int allTotal34a = getDestination(allData, "29");
				int allTotal35 = getDestination(allData, "24");
				int allTotal36 = getDestination(allData, "17");
				int allTotal37 = getDestination(allData, "8","9");
				int allTotal38 = getDestination(allData, "30","99");
				
				int withoutChildren34a = getDestination(withoutChildren, "29");
				int withoutChildren35 = getDestination(withoutChildren, "24");
				int withoutChildren36 = getDestination(withoutChildren, "17");
				int withoutChildren37 = getDestination(withoutChildren, "8","9");
				int withoutChildren38 = getDestination(withoutChildren, "30","99");
				
				int withChildAndAdult34a = getDestination(withChildAndAdults, "29");
				int withChildAndAdult35 = getDestination(withChildAndAdults, "24");
				int withChildAndAdult36 = getDestination(withChildAndAdults, "17");
				int withChildAndAdult37 = getDestination(withChildAndAdults, "8","9");
				int withChildAndAdult38 = getDestination(withChildAndAdults, "30","99");
				
				int withChildren34a = getDestination(withChildren, "29");
				int withChildren35 = getDestination(withChildren, "24");
				int withChildren36 = getDestination(withChildren, "17");
				int withChildren37 = getDestination(withChildren,"8","9");
				int withChildren38 = getDestination(withChildren, "30","99");
				
				int unknown34a = getDestination(unknown, "29");
				int unknown35 = getDestination(unknown, "24");
				int unknown36 = getDestination(unknown, "17");
				int unknown37 = getDestination(unknown, "8","9");
				int unknown38 = getDestination(unknown, "30","99");
				
				
				int subTotal4AllData =  allTotal34a+ allTotal35 + allTotal36 + allTotal37 + allTotal38 ;
				int subTotal4WithoutChild =  withoutChildren34a+ withoutChildren35 + withoutChildren36 + withoutChildren37 + withoutChildren38  ;
				int subTotal4WithChildAndAdults = withChildAndAdult34a + withChildAndAdult35 + withChildAndAdult36 + withChildAndAdult37 + withChildAndAdult38 ;
				int subTotal4WithChildren =  withChildren34a + withChildren35  + withChildren36 + withChildren37 + withChildren38 ;
				int subTotal4Unknown = unknown34a + unknown35 + unknown36 + unknown37 + unknown38 ;
				
				// Other Destination sub total above
				//34
				q23cDataBean.setQ23CD1ResidentialProjectorHalfwayHouseTotal(BigInteger.valueOf(allTotal34a));
				q23cDataBean.setQ23CD1ResidentialProjectorHalfwayHouseWithoutChild(BigInteger.valueOf(withoutChildren34a));
				q23cDataBean.setQ23CD1ResidentialProjectorHalfwayHouseWithChildAndAdults(BigInteger.valueOf(withChildAndAdult34a));
				q23cDataBean.setQ23CD1ResidentialProjectorHalfwayHouseWithOnlyChild(BigInteger.valueOf(withChildren34a));
				q23cDataBean.setQ23CD1ResidentialProjectorHalfwayHouseUnknownHouseHold(BigInteger.valueOf(unknown34a));
				//35
				q23cDataBean.setQ23CD2DeceasedTotal(BigInteger.valueOf(allTotal35));
				q23cDataBean.setQ23CD2DeceasedWithoutChild(BigInteger.valueOf(withoutChildren35));
				q23cDataBean.setQ23CD2DeceasedWithChildAndAdults(BigInteger.valueOf(withChildAndAdult36));
				q23cDataBean.setQ23CD2DeceasedWithOnlyChild(BigInteger.valueOf(withChildren35));
				q23cDataBean.setQ23CD2DeceasedUnknownHouseHold(BigInteger.valueOf(unknown35));
				//36
				q23cDataBean.setQ23CD3OtherTotal(BigInteger.valueOf(allTotal36));
				q23cDataBean.setQ23CD3OtherWithoutChild(BigInteger.valueOf(withoutChildren36));
				q23cDataBean.setQ23CD3OtherWithChildAndAdults(BigInteger.valueOf(withChildAndAdult36));
				q23cDataBean.setQ23CD3OtherWithOnlyChild(BigInteger.valueOf(withChildren36));
				q23cDataBean.setQ23CD3OtherUnknownHouseHold(BigInteger.valueOf(unknown36));
				//37
				q23cDataBean.setQ23CD4DKRTotal(BigInteger.valueOf(allTotal37));
				q23cDataBean.setQ23CD4DKRWithoutChild(BigInteger.valueOf(withoutChildren37));
				q23cDataBean.setQ23CD4DKRWithChildAndAdults(BigInteger.valueOf(withChildAndAdult37));
				q23cDataBean.setQ23CD4DKRWithOnlyChild(BigInteger.valueOf(withChildren37));
				q23cDataBean.setQ23CD4DKRUnknownHouseHold(BigInteger.valueOf(unknown37));
				//38
				q23cDataBean.setQ23CD5InfoMissingTotal(BigInteger.valueOf(allTotal38));
				q23cDataBean.setQ23CD5InfoMissingWithoutChild(BigInteger.valueOf(withoutChildren38));
				q23cDataBean.setQ23CD5InfoMissingWithChildAndAdults(BigInteger.valueOf(withChildAndAdult38));
				q23cDataBean.setQ23CD5InfoMissingWithOnlyChild(BigInteger.valueOf(withChildren38));
				q23cDataBean.setQ23CD5InfoMissingUnknownHouseHold(BigInteger.valueOf(unknown38));

				//39
				q23cDataBean.setQ23CDzTotTotal(BigInteger.valueOf(subTotal4AllData));
				q23cDataBean.setQ23CDzTotWithoutChild(BigInteger.valueOf(subTotal4WithoutChild));
				q23cDataBean.setQ23CDzTotWithChildAndAdults(BigInteger.valueOf(subTotal4WithChildAndAdults));
				q23cDataBean.setQ23CDzTotWithOnlyChild(BigInteger.valueOf(subTotal4WithChildren));
				q23cDataBean.setQ23CDzTotUnknownHouseHold(BigInteger.valueOf(subTotal4Unknown));
				
				int totalAllData = subTotal1AllData + subTotal2AllData + subTotal3AllData + subTotal4AllData ;
				int totalWithoutChild = subTotal1WithoutChild + subTotal2WithoutChild + subTotal3WithoutChild + subTotal4WithoutChild;
				int totalWithChildAndAdults = subTotal1WithChildAndAdults + subTotal2WithChildAndAdults + subTotal3WithChildAndAdults + subTotal4WithChildAndAdults;
				int totalWithChildren = subTotal1WithChildren + subTotal2WithChildren + subTotal3WithChildren + subTotal4WithChildren;
				int totalUnknown = subTotal1Unknown + subTotal2Unknown + subTotal3Unknown + subTotal4Unknown ;
				//40
				q23cDataBean.setQ23CETotTotal(BigInteger.valueOf(getSize(data.getAdultLeavers())));
				q23cDataBean.setQ23CETotWithoutChild(BigInteger.valueOf(totalWithoutChild));
				q23cDataBean.setQ23CETotWithChildAndAdults(BigInteger.valueOf(totalWithChildAndAdults));
				q23cDataBean.setQ23CETotWithOnlyChild(BigInteger.valueOf(totalWithChildren));
				q23cDataBean.setQ23CETotUnknownHouseHold(BigInteger.valueOf(totalUnknown));
				
				List<String> positiveDestinationTypes = new ArrayList<String>();
				positiveDestinationTypes.add("26");
				positiveDestinationTypes.add("11");
				positiveDestinationTypes.add("21");
				positiveDestinationTypes.add("10");
				positiveDestinationTypes.add("19");
				positiveDestinationTypes.add("28");
				positiveDestinationTypes.add("20");
				positiveDestinationTypes.add("3");
				positiveDestinationTypes.add("22");
				positiveDestinationTypes.add("23");
				positiveDestinationTypes.add("31");
				
				List<String> tempInstDestinationTypes = new ArrayList<String>();
				tempInstDestinationTypes.add("1");
				tempInstDestinationTypes.add("15");
				tempInstDestinationTypes.add("14");
				tempInstDestinationTypes.add("7");
				tempInstDestinationTypes.add("27");
				tempInstDestinationTypes.add("16");
				tempInstDestinationTypes.add("4");
				tempInstDestinationTypes.add("29");
				tempInstDestinationTypes.add("18");
				tempInstDestinationTypes.add("12");
				tempInstDestinationTypes.add("13");
				tempInstDestinationTypes.add("5");
				tempInstDestinationTypes.add("2");
				tempInstDestinationTypes.add("25");
			
				int destinationByDestinationTypeAllData = getDestinationByDestinationType(allData, positiveDestinationTypes);
				int destinationByProjectTypeAllData = getDestinationByProjectType(allData, tempInstDestinationTypes, "6");
				int positiveHousingAllData = destinationByDestinationTypeAllData + destinationByProjectTypeAllData ;
				
				int destinationByDestinationTypeWithoutChildren = getDestinationByDestinationType(withoutChildren, positiveDestinationTypes);
				int destinationByProjectTypeWithoutChildren = getDestinationByProjectType(withoutChildren, tempInstDestinationTypes, "6");
				int positiveHousingWithoutChildren = destinationByDestinationTypeWithoutChildren + destinationByProjectTypeWithoutChildren ;
				
				int destinationByDestinationTypeWithChildAndAdults = getDestinationByDestinationType(withChildAndAdults, positiveDestinationTypes);
				int destinationByProjectTypeWithChildAndAdults = getDestinationByProjectType(withChildAndAdults, tempInstDestinationTypes, "6");
				int positiveHousingWithChildAndAdults = destinationByDestinationTypeWithChildAndAdults + destinationByProjectTypeWithChildAndAdults ;
				
				int destinationByDestinationTypeWithChildren = getDestinationByDestinationType(withChildren, positiveDestinationTypes);
				int destinationByProjectTypeWithChildren = getDestinationByProjectType(withChildren, tempInstDestinationTypes, "6");
				int positiveHousingWithChildren = destinationByDestinationTypeWithChildren + destinationByProjectTypeWithChildren ;
				
				int destinationByDestinationTypeUnknown = getDestinationByDestinationType(unknown, positiveDestinationTypes);
				int destinationByProjectTypeUnknown = getDestinationByProjectType(unknown, tempInstDestinationTypes, "6");
				int positiveHousingWithoutUnknown = destinationByDestinationTypeUnknown + destinationByProjectTypeUnknown ;
				
				//41
				q23cDataBean.setQ23CTotPersonsExistUnknownHouseHold(BigInteger.valueOf(positiveHousingAllData));
				q23cDataBean.setQ23CTotPersonsExistWithOnlyChild(BigInteger.valueOf(positiveHousingWithoutChildren));
				q23cDataBean.setQ23CTotPersonsExistWithChildAndAdults(BigInteger.valueOf(positiveHousingWithChildAndAdults));
				q23cDataBean.setQ23CTotPersonsExistWithoutChild(BigInteger.valueOf(positiveHousingWithChildren));
				q23cDataBean.setQ23CTotPersonsExistTotal(BigInteger.valueOf(positiveHousingWithoutUnknown));
				
				List<String> destinationTypeExcluded = new ArrayList<>();
				destinationTypeExcluded.add("6");
				destinationTypeExcluded.add("24");
				
				
				List<String> projectTypeExcluded = new ArrayList<>();
				projectTypeExcluded.add("1");
				projectTypeExcluded.add("2");
				projectTypeExcluded.add("8");
				projectTypeExcluded.add("9");
				projectTypeExcluded.add("10");
				
				int clientsExcludedAllData = getDestinationByDestinationType(allData, destinationTypeExcluded);
				int projectTypeClientsExcludedAllData = getDestinationByProjectTypes(allData, destinationTypeExcluded, projectTypeExcluded);
				int destinationClientsExcludedAllData = clientsExcludedAllData + projectTypeClientsExcludedAllData ;
				
				int clientsExcludedwithoutChildren = getDestinationByDestinationType(withoutChildren, destinationTypeExcluded);
				int projectTypeClientsExcludedwithoutChildren = getDestinationByProjectTypes(withoutChildren, destinationTypeExcluded, projectTypeExcluded);
				int destinationClientsExcludedwithoutChildren = clientsExcludedwithoutChildren + projectTypeClientsExcludedwithoutChildren ;
				
				int clientsExcludedeWithOnlyChild = getDestinationByDestinationType(withChildren, destinationTypeExcluded);
				int projectTypeClientsExcludedeWithOnlyChild = getDestinationByProjectTypes(withChildren, destinationTypeExcluded, projectTypeExcluded);
				int destinationClientsExcludedeWithOnlyChild = clientsExcludedeWithOnlyChild + projectTypeClientsExcludedeWithOnlyChild ;
				
				int clientsExcludedWithChildAndAdults = getDestinationByDestinationType(withChildAndAdults, destinationTypeExcluded);
				int projectTypeClientsExcludedWithChildAndAdults = getDestinationByProjectTypes(withChildAndAdults, destinationTypeExcluded, projectTypeExcluded);
				int destinationClientsExcludedWithChildAndAdults = clientsExcludedWithChildAndAdults + projectTypeClientsExcludedWithChildAndAdults ;
				
				int clientsExcludedWithUnknown = getDestinationByDestinationType(unknown, destinationTypeExcluded);
				int projectTypeClientsExcludedWithUnknown = getDestinationByProjectTypes(unknown, destinationTypeExcluded, projectTypeExcluded);
				int destinationClientsExcludedWithUnknown = clientsExcludedWithUnknown + projectTypeClientsExcludedWithUnknown ;
				
				
				//42
				q23cDataBean.setQ23CTotPersonsDstnsWithoutChild(BigInteger.valueOf(destinationClientsExcludedwithoutChildren));
				q23cDataBean.setQ23CTotPersonsDstnsTotal(BigInteger.valueOf(destinationClientsExcludedAllData));
				q23cDataBean.setQ23CTotPersonsDstnsUnknownHouseHold(BigInteger.valueOf(destinationClientsExcludedWithUnknown));
				q23cDataBean.setQ23CTotPersonsDstnsWithOnlyChild(BigInteger.valueOf(destinationClientsExcludedeWithOnlyChild));
				q23cDataBean.setQ23CTotPersonsDstnsWithChildAndAdults(BigInteger.valueOf(destinationClientsExcludedWithChildAndAdults));
				
				// percentage below.
				//43 = 41/40-42
				q23cDataBean.setQ23CPercentageWithChildAndAdults(BigInteger.valueOf(divide(positiveHousingWithChildAndAdults, totalWithChildAndAdults, destinationClientsExcludedWithChildAndAdults)));
				q23cDataBean.setQ23CPercentageUnknownHouseHold(BigInteger.valueOf(divide(positiveHousingWithoutUnknown, totalUnknown, destinationClientsExcludedWithUnknown)));
				q23cDataBean.setQ23CPercentageWithOnlyChild(BigInteger.valueOf(divide(positiveHousingWithChildren, totalWithChildren, destinationClientsExcludedeWithOnlyChild)));
				q23cDataBean.setQ23CPercentageWithoutChild(BigInteger.valueOf(divide(positiveHousingWithoutChildren, totalWithoutChild, destinationClientsExcludedwithoutChildren)));
				q23cDataBean.setQ23CPercentageTotal(BigInteger.valueOf(divide(positiveHousingAllData, totalAllData, destinationClientsExcludedAllData)));
		
			}	}catch(Exception e){
				logger.error("Error in Q22dLengthOfParticipationByHouseholdTypeDataBeanMaker:" + e);
			}
			return Arrays.asList(q23cDataBean);
	
	}
	
	
	public static int  getDestination (List<Q22BeanModel> q22Beans ,String destination) {
		if(CollectionUtils.isNotEmpty(q22Beans)) {
			List<Q22BeanModel>  q22Bean7DaysOrLessAllData = q22Beans.parallelStream().filter(q22BeanModel ->  StringUtils.equals(destination, q22BeanModel.getDestination())).collect(Collectors.toList());
			return q22Bean7DaysOrLessAllData.size();
		}
		 return 0;
	}

	public static int  getDestination (List<Q22BeanModel> q22Beans , String destination1,String destination2) {
		if(CollectionUtils.isNotEmpty(q22Beans)) {
			List<Q22BeanModel>  q22Bean7DaysOrLessAllData = q22Beans.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() < numOfDays  && ( StringUtils.equals(destination1, q22BeanModel.getDestination())  || StringUtils.equals(destination2, q22BeanModel.getDestination()) )).collect(Collectors.toList());
			return q22Bean7DaysOrLessAllData.size();
		}
		 return 0;
	}
	
	public static int  getDestinationByDestinationType(List<Q22BeanModel> q22Beans , List<String> destinations) {
		if(CollectionUtils.isNotEmpty(q22Beans)) {
			List<Q22BeanModel>  q22Bean7DaysOrLessAllData = q22Beans.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() < numOfDays  && ( destinations.contains(q22BeanModel.getDestination()))).collect(Collectors.toList());
			return q22Bean7DaysOrLessAllData.size();
		}
		 return 0;
	}
	
	public static int  getDestinationByProjectType(List<Q22BeanModel> q22Beans , List<String> destinations,String projectType) {
		if(CollectionUtils.isNotEmpty(q22Beans)) {
			List<Q22BeanModel>  q22Bean7DaysOrLessAllData = q22Beans.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() < numOfDays  && ( destinations.contains(q22BeanModel.getDestination())) && StringUtils.equals(projectType, q22BeanModel.getProjectType()) ).collect(Collectors.toList());
			return q22Bean7DaysOrLessAllData.size();
		}
		 return 0;
	}
	
	public static int  getDestinationByProjectTypes(List<Q22BeanModel> q22Beans , List<String> destinations,List<String> projectTypes) {
		if(CollectionUtils.isNotEmpty(q22Beans)) {
			List<Q22BeanModel>  q22Bean7DaysOrLessAllData = q22Beans.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() < numOfDays  && ( destinations.contains(q22BeanModel.getDestination())) && projectTypes.contains(q22BeanModel.getProjectType()) ).collect(Collectors.toList());
			return q22Bean7DaysOrLessAllData.size();
		}
		 return 0;
	}
	
	public static long  divide(int destination41 , int destination40,int destination42) {
		if(destination41 == 0){
			 return 0;
		}
		int subTotal = (destination40 - destination42);
		if(subTotal <= 0) {
			return 0;
		}
		long percentage = destination41/subTotal;
		return percentage;
	}
}
