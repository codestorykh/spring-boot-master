package com.rean.springbootmaster.user.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

@Configuration
public class UserProvider {

    public HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.getEncoder().encode(
                    auth.getBytes(StandardCharsets.UTF_8));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader );
        }};
    }

    public UserResponse fetchUsersFromFakeAPI() {
        try {
            // request url
            var requestUrl = "http://localhost:8080/api/v1/users";
            // create headers with basic auth
            HttpHeaders headers = createHeaders("admin", "admin");
            // create request
            HttpEntity<Objects> httpEntity = new HttpEntity<>(headers);
            // create rest template
            RestTemplate restTemplate = new RestTemplate();
            // send request
            ResponseEntity<String> responseEntity = restTemplate
                    .exchange(requestUrl,
                            HttpMethod.GET, httpEntity, String.class);
            // print response
            System.out.println(responseEntity.getBody());
            // convert response to object
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(responseEntity.getBody(), UserResponse.class);
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException("Failed to fetch users from fake API");
        }
    }

    public static class UserResponse {
        private String id;
        private String name;
        private String email;
        private String phone;
        private String address;
        private String status;
        private String role;
        private String createdDate;
        private String updatedDate;

        // getter and setter

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getUpdatedDate() {
            return updatedDate;
        }

        public void setUpdatedDate(String updatedDate) {
            this.updatedDate = updatedDate;
        }

        @Override
        public String toString() {
            return "UserResponse{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", phone='" + phone + '\'' +
                    ", address='" + address + '\'' +
                    ", status='" + status + '\'' +
                    ", role='" + role + '\'' +
                    ", createdDate='" + createdDate + '\'' +
                    ", updatedDate='" + updatedDate + '\'' +
                    '}';
        }
    }
}
