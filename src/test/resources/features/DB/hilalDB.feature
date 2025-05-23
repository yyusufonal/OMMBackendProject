Feature: US_002,US_025 and US_040 Database testing

    Scenario: Grouping the services in the services table according to their IDs, verify the coupon name and end_date information in the service_coupons table of the services that created the coupon
        Given Database connection established.
        When The user executes the query to get services with their coupons
        Then The user verifies that the services are grouped by their IDs
        And The user verifies that each service has valid coupon information
        And The user verifies that the coupon end dates are in correct format
        And Database closed.


