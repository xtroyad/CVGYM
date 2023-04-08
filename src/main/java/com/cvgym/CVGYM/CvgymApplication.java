package com.cvgym.CVGYM;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@SpringBootApplication
public class CvgymApplication {

    public static void main(String[] args) throws FileNotFoundException {
        dataLoader();
        SpringApplication.run(CvgymApplication.class, args);
	}

    public static void dataLoader() throws FileNotFoundException {
        File file = new File("src\\main\\java\\com\\cvgym\\CVGYM\\dataLoader\\gym.json");
        Scanner sc = new Scanner(file);
        StringBuilder information = new StringBuilder();
        while (sc.hasNext()) {
            information.append((sc.nextLine()));
        }
        sc.close();
        JSONArray jsonArray = new JSONArray(information.toString());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject e = jsonArray.getJSONObject(i);

            System.out.println(e.getString("city"));
            System.out.println(e.getString("ccaa"));
            System.out.println(e.getString("address"));
            System.out.println(e.getString("number"));
            System.out.println(e.getString("zip"));
            System.out.println(e.getString("phoneNumber"));

        }

    }

}
