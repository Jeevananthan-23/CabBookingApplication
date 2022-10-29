# Cab Booking Application

### Description:

Implement a cab booking application. Below are the expected features from the system.

### Features:
* The application allows users to book rides on a route.
* Users can register themself.
* Driving partner can onboard on the system with the vehicle details
* Users can search and select one from multiple available rides on a route with the same source and destination based on the nearest to the user


### Requirements:
1. Application should allow user onboarding.
      - `add_user(user_detail)`
        - Add basic user details
2. Application should allow Driver onboarding
      - `add_driver(driver_details,vehicle_details,current_location)`
          - This will create an instance of the driver and will mark his current location on the map      
3. Application should allow the user to find a ride based on the criteria below
      - `find_ride (Username,Source , destination)`
         - It will return a list of available ride 
      - `choose_ride(Username,drive_name)`
          - It will choose the drive name from the list 
