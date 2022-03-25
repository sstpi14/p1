package models;

public enum UserRole {

    EMPLOYEE {
        @Override
        public String toString() {
            return "Employee";
        }
    },

    FINANCE_MANAGER {
        @Override
        public String toString() {
            return "Finance Manager";
        }
    }
}
