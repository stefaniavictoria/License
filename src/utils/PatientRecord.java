package utils;

public class PatientRecord {
	
        private String patientName;
        private String contact;
        private String gender;
        private String dateOfBirth;
        private String address;
        private String testResults;

        public PatientRecord(String patientName, String contact, String gender, String dateOfBirth, String address, String testResults) {
            this.patientName = patientName;
            this.contact = contact;
            this.gender = gender;
            this.dateOfBirth = dateOfBirth;
            this.address = address;
            this.testResults = testResults;
        }

        public String getPatientName() {
            return patientName;
        }

        public String getContact() {
            return contact;
        }

        public String getGender() {
            return gender;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public String getAddress() {
            return address;
        }

        public String getTestResults() {
            return testResults;
        }
    }
