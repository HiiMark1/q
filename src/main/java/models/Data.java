package models;

import java.util.ArrayList;

public class Data extends ArrayList<Data> {
      public String updated_at;
      public Bus data;

      public String getUpdated_at() {
            return updated_at;
      }

      public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
      }

      public Bus getData() {
            return data;
      }

      public void setData(Bus data) {
            this.data = data;
      }
}
