Feature: US_002,US_025 and US_040 Database testing

    Scenario: Grouping the services in the services table according to their IDs, verify the coupon name and end_date information in the service_coupons table of the services that created the coupon
        Given Database connection established.
        When The user executes the query to get services with their coupons
        Then The user verifies that the services are grouped by their IDs
        And The user verifies that each service has valid coupon information
        And The user verifies that the coupon end dates are in correct format
        And Database closed.

    Scenario: List the coupons that have reached the maximum user limit
        Given Database connection established and coupon limit updated.
        When The user executes the query to get coupons that reached maximum user limit
        Then The user verifies that the coupons have reached their user limit
        And The user verifies that the user limit count is greater than or equal to user limit
        And Database closed.
