package com.example.m4.models;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nickstoltz on 4/4/17.
 */

public class DataBaseRequests {

    //public static String url = "http://nstoltzfus3.pythonanywhere.com/";


    public static void registerUsers(final Context context){
        String myURL = "http://nstoltzfus3.pythonanywhere.com/getusers";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, myURL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast out = Toast.makeText(context, "Database Connection Successful", Toast.LENGTH_SHORT);
                            out.show();

                            JSONArray allUsers = response.getJSONArray("allUsers");

                            for (int i = 0; i < allUsers.length(); i++) {
                                JSONObject user = (JSONObject) allUsers.get(i);
                                String username = user.getString("user_name");
                                String password = user.getString("password");
                                String address = user.getString("address");
                                String title = user.getString("title");
                                String email = user.getString("email");
                                String accountType = user.getString("account_type");

                                AccountType newAccount = new User();

                                if (accountType.equals("Administrator")) {
                                    newAccount = new Administrator();
                                } else if (accountType.equals("Worker")) {
                                    newAccount = new Worker();
                                } else if (accountType.equals("Manager")) {
                                    newAccount = new Manager();
                                } else {
                                    newAccount = new User();
                                }

                                System.out.println("Username: " + username);
                                System.out.println("Password: " + password);
                                System.out.println("accountType: " + newAccount);

                                newAccount.setUsername(username);
                                newAccount.setPassword(password);


                                boolean registerSuccess = Models.register(newAccount);

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast out = Toast.makeText(context, "Database Connection Failed", Toast.LENGTH_SHORT);
                        out.show();
                        // TODO Auto-generated method stub

                    }
                });
        MySingleton.getInstance(context).addToRequestQueue(jsObjRequest);
    }

    public static void createReport(final Context context){
        String myURL = "http://nstoltzfus3.pythonanywhere.com/getreports";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, myURL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast out = Toast.makeText(context, "Database Connection Successful", Toast.LENGTH_SHORT);
                            out.show();

                            JSONArray allReports = response.getJSONArray("allReports");

                            for (int i = 0; i < allReports.length(); i++) {
                                JSONObject report = (JSONObject) allReports.get(i);
                                String reportType = report.getString("report_type");
                                String waterCondition = report.getString("water_condition");
                                Double virusPPM = report.getDouble("virus_ppm");
                                Double contaminantPPM = report.getDouble("contaminant_ppm");
                                String waterType = report.getString("waterType");
                                String reporterUser = report.getString("reporter_username");
                                Integer reportNumber = report.getInt("report_number");
                                String dateString = report.getString("date");
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Integer year = report.getInt("year");
                                Integer month = report.getInt("month");
                                String location = report.getString("location");
                                Double latitude = report.getDouble("latitude");
                                Double longitude = report.getDouble("longitude");

                                if (reportType.equals("Water Purity Report")) {
                                   WaterPurityReport newReport = new WaterPurityReport();
                                    newReport.setWaterOverallCondition(WaterOverallCondition.valueOf(waterCondition));
                                    newReport.setVirusPPM(virusPPM);
                                    newReport.setContaminantPPM(contaminantPPM);
                                    newReport.setReporterUsername(reporterUser);
                                    newReport.setReportNumber(reportNumber);
                                    newReport.setDate(sdf.parse(dateString));
                                    newReport.setYear(year);
                                    newReport.setMonth(month);
                                    newReport.setLocation(location);
                                    newReport.setLongitude(longitude);
                                    newReport.setLatitude(latitude);
                                    boolean addReport = Models.submitReport(newReport);


                                } else if (reportType.equals("Water Source Report")) {
                                    WaterSourceReport newReport = new WaterSourceReport();
                                    newReport.setWaterCondition(WaterCondition.valueOf(waterCondition));
                                    newReport.setWaterType(WaterType.valueOf(waterType));
                                    newReport.setReporterUsername(reporterUser);
                                    newReport.setReportNumber(reportNumber);
                                    newReport.setDate(sdf.parse(dateString));
                                    newReport.setYear(year);
                                    newReport.setMonth(month);
                                    newReport.setLocation(location);
                                    newReport.setLongitude(longitude);
                                    newReport.setLatitude(latitude);
                                    boolean addReport = Models.submitReport(newReport);


                                } else {
                                    System.out.println("No valid report");
                                }

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast out = Toast.makeText(context, "Database Connection Failed", Toast.LENGTH_SHORT);
                        out.show();
                        // TODO Auto-generated method stub

                    }
                });
        MySingleton.getInstance(context).addToRequestQueue(jsObjRequest);
    }
}