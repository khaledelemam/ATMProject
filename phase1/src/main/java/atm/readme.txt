----- How to use the program -----

- program will close at system's midnight
- assumed each month has 31 days

--- How to run as admin ---

* must log in before running any admin tasks *
    > password: 123
    > set date if the date did not show in the home screen (only one time)

(1) Approve new user requests
(2) Approve account requests for existing users
(3) Reverse transactions
(4) Set the date
(5) Restock denominations

--- How to register as a user ---

(1) Login as a user
*default password for user is "1"*
(2) Request a new user account
(3) Wait until administrator approves your account, based on your credit score
* you have to login as an admin to approve the user request *
(5) Once approved by the admin, you can login and will be given your temporary password

--- What you can do as a registered user ---

- View your account information:
    > Current balance
    > Date your account was opened
    > Your last transaction

- Transfer money:
    > Between your existing accounts
    > To another user (Recipient user must be registered in the system)

- Withdraw money from any of your accounts
    > Cash withdrawn must be a valid combination of the available denominations: $5, $10, $20, $50

- Pay your bills
    > Bill payments will be recorded in "outgoing.txt"

- Open a new account
    > Only one account can be requested at a time per user

- Change your password

- Have money deposited to your account
    > Cash/cheques are deposited according to what is in the file "deposits.txt"
    > Only one bill can be deposited at a time, and must be a valid denomination ($5, $10, $20, $50)
    > FILE FORMAT: user type amount
        > type can be "cash" or "cheque"
        > amount does not include "$"
        > deposit only occurs if the user exists in the system already

--- Other features ---