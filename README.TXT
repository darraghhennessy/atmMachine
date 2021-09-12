To run this project you will need Java and Maven installed on your computer.

After cloning the repo run:
mvn install

Then to get docker running run:
docker-compose up -d

The app will be hosted at localhost:8080

To run the unit tests run:
mvn test

//////////////////////////////////////////
The app accepts the following GET commands:
/balance
Params are accountNo and pin.

e.g.
http://localhost:8080/balance?accountNo=123456789&pin=1234

If the accountNo and matching pin are correct, this command will return the account's opening balance.



/max
Params are accountNo and pin.

e.g.
http://localhost:8080/max?accountNo=123456789&pin=1234

If the accountNo and matching pin are correct, this command will return the maximum the user can withdraw from this account.



/withdraw
Params are accountNo, pin and amount

e.g.
http://localhost:8080/max?accountNo=123456789&pin=1234&amount=500

If the accountNo and matching pin are correct (and the amount requested can be provided) this command will dispense the requested funds, in addition to information about what notes are dispensed.
