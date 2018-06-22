package com.example.jinsungjun.httpurlconnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Remote {

    public static String get(String urlString) {

        String result = "";
        try {
            //1. url의 프로토콜 더해주기
            if (!urlString.startsWith("http")) {

                urlString = "http://" + urlString;
            }

            //2. URL 객체 만들기
            URL url = new URL(urlString);

            //3. http 커넥션 만들기
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //4. 연결 옵션 설정
            connection.setRequestMethod("GET");

            //5. 처리 결과 꺼내기
            int resCode = connection.getResponseCode();

            //6. 처리 결과에 따른 분기처리
            if (resCode == HttpURLConnection.HTTP_OK) {
                //6.1 성공 시
                // 연결된 스트림을 꺼낸다
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                //반복문을 돌면서 줄단위로 result 변수에 저장한다
                while((line = br.readLine()) != null) {

                    result = result + line + "\n";
                }

            } else {
                //6.2 실패 시
                result = "Error code" + resCode;
            }
        }catch (Exception e) {

            result = e.getMessage();
        }

        return result;
    }
}
