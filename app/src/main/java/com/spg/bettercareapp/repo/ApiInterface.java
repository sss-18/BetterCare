package com.spg.bettercareapp.repo;

import com.spg.bettercareapp.model.Accident;
import com.spg.bettercareapp.model.Admin;
import com.spg.bettercareapp.model.Care;
import com.spg.bettercareapp.model.Catalogue;
import com.spg.bettercareapp.model.DailyActivity;
import com.spg.bettercareapp.model.Fluid;
import com.spg.bettercareapp.model.Meal;
import com.spg.bettercareapp.model.Mood;
import com.spg.bettercareapp.model.OtherComment;
import com.spg.bettercareapp.model.PersonalCare;
import com.spg.bettercareapp.model.Resident;
import com.spg.bettercareapp.model.Visit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("authadmin.php")
    Call<List<Resident>> authAdmin(@Query("user_name") String user_name);

    @GET("authcarestaff.php")
    Call<List<Resident>> authCareStaff(@Query("user_name") String user_name);

    @GET("getaccident.php")
    Call<List<Accident>> getAccidents(@Query("resident_id") String resident_id, @Query("date") String date);

    @GET("getcatalogue.php")
    Call<List<Catalogue>> getCatalogues(@Query("resident_id") String resident_id, @Query("date") String date);

    @GET("getdailyactivity.php")
    Call<List<DailyActivity>> getDailyActivity(@Query("resident_id") String resident_id, @Query("date") String date);

    @GET("getfluid.php")
    Call<List<Fluid>> getFluids(@Query("resident_id") String resident_id, @Query("date") String date);

    @GET("getmeal.php")
    Call<List<Meal>> getMeals(@Query("resident_id") String resident_id, @Query("date") String date);

    @GET("getmood.php")
    Call<List<Mood>> getMoods(@Query("resident_id") String resident_id, @Query("date") String date);

    @GET("getothercomment.php")
    Call<List<OtherComment>> getComments(@Query("resident_id") String resident_id, @Query("date") String date);

    @GET("getpersonalcare.php")
    Call<List<PersonalCare>> getPersonalCare(@Query("resident_id") String resident_id, @Query("date") String date);

    @GET("getresident.php")
    Call<List<Resident>> getResidents();

    @GET("getvisit.php")
    Call<List<Visit>> getVisits(@Query("resident_id") String resident_id, @Query("date") String date);

    @GET("removeresident.php")
    Call<List<Resident>> deleteResident(@Query("resident_id") String resident_id);

    @GET("setadmin.php")
    Call<List<Admin>> setAdmin(@Query("user_name") String user_name, @Query("password") String password);

    @GET("setcarestaff.php")
    Call<List<Care>> setCareStaff(@Query("user_name") String user_name, @Query("password") String password);

    @GET("setpersonalcare.php")
    Call<List<PersonalCare>> setPersonalCare(@Query("date") String date,
                                             @Query("resident_id") String resident_id,
                                             @Query("bathing") String bathing,
                                             @Query("skin_care") String skin_care,
                                             @Query("oral_care") String oral_care,
                                             @Query("dressing") String dressing,
                                             @Query("pad_change") String pad_change,
                                             @Query("hair_care") String hair_care);

    @GET("getmood.php")
    Call<List<Resident>> setResident(@Query("name") String name,
                                     @Query("dob") String dob,
                                     @Query("care_type") String care_type,
                                     @Query("sex") String sex,
                                     @Query("room_no") String room_no);
}
