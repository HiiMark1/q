import com.google.gson.Gson;
import models.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

public class JSONHelper {
      ArrayList<Data> dataArrayList;

      JSONHelper() {
            updateInfo();
      }

      public void updateInfo() {
            String json = getInfo();
            if (json.equals("Error!")) {
                  dataArrayList = null;
            } else {
                  {
                        Gson gson = new Gson();
                        json = json.substring(1, json.length() - 1);
                        String[] jsons = json.split("},");
                        for (int i = 0; i < jsons.length - 1; i++) {
                              jsons[i] = jsons[i] + "}";
                              dataArrayList.add(gson.fromJson(jsons[i], Data.class));
                        }
                        dataArrayList.add(gson.fromJson(jsons[jsons.length - 1], Data.class));
                        System.out.println(dataArrayList.get(1));
                  }
            }
      }

      private String getInfo() {
            StringBuilder jsonString = new StringBuilder();
            try {
                  URL getUrl = new URL("http://data.kzn.ru:8082/api/v0/dynamic_datasets/bus.json");
                  HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
                  connection.setRequestProperty("Content-Type", "application/json");
                  connection.setRequestMethod("GET");
                  connection.setConnectTimeout(120000);
                  connection.setReadTimeout(120000);
                  try (BufferedReader reader = new BufferedReader(
                          new InputStreamReader(connection.getInputStream())
                  )) {
                        String input;
                        while ((input = reader.readLine()) != null) {
                              jsonString.append(input);
                        }
                  }
                  connection.disconnect();
            } catch (IOException e) {
                  return "Error!";
            }
            return jsonString.toString();
      }

      public ArrayList<Data> getBusesForMarsh(String marsh) {
            ArrayList<Data> dataArrayList1 = new ArrayList<>();
            if (dataArrayList != null) {
                  for (Data d : dataArrayList) {
                        if (d.getData().getMarsh().equals(marsh)) {
                              dataArrayList1.add(d);
                        }
                  }
            }
            return dataArrayList1;
      }
}
