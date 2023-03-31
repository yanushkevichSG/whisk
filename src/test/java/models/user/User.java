package models.user;

import utilities.Environment;

public class User {
    private String firstName;
    private String lastName;
    private Credentials credentials;
    private String phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return credentials.getEmail();
    }

    public String getPassword() {
        return credentials.getPassword();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    public static User defaultUser() {
        return User.builder()
                .withCredentials(new Credentials(Environment.getUserEmail(),
                        Environment.getUserPassword()))
                .build();
    }

    public static NewUserBuilder builder() {
        return new User().new NewUserBuilder();
    }

    public class NewUserBuilder {

        public NewUserBuilder withFirstName(String firstName) {
            User.this.firstName = firstName;
            return this;
        }

        public NewUserBuilder withLastName(String lastName) {
            User.this.lastName = lastName;
            return this;
        }

        public NewUserBuilder withPhoneNumber(String phoneNumber) {
            User.this.phoneNumber = phoneNumber;
            return this;
        }

        public NewUserBuilder withCredentials(Credentials credentials) {
            User.this.credentials = credentials;
            return this;
        }

        public User build() {
            return User.this;
        }
    }
}