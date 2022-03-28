CREATE TABLE IF NOT EXISTS client
(
    clientID  serial primary key,
    firstname varchar(20),
    lastName  varchar(20),
    address   varchar(30)
);



CREATE TABLE IF NOT EXISTS Account
(
    accountID serial primary key,
    balance   bigint,
    clientID  int,
    branchID  int,
    constraint fk1 foreign key (clientID) references client (clientID),
    constraint fk2 foreign key (branchID) references branch (branchID)
);


CREATE TABLE IF NOT EXISTS branch
(
    branchID serial primary key,
    name     varchar(20),
    address  varchar(30)
);

CREATE TABLE IF NOT EXISTS boss
(
    bossID    serial primary key,
    firstName varchar(20),
    lastName  varchar(20),
    address   varchar(30),
    branchID  int unique,
    constraint fk foreign key (branchID) references branch (branchID)
);


CREATE TABLE IF NOT EXISTS creditCard
(
    ccNumber             bigint unique,
    cvv2                 int,
    expireDate           date,
    password             int,
    accountID            int unique,
    wrongPasswordEntries int,
    constraint fk1 foreign key (accountID) references Account (accountID)
);

CREATE TABLE IF NOT EXISTS employee
(
    employeeID serial primary key,
    firstName  varchar(20),
    lastName   varchar(20),
    address    varchar(30),
    bossID     int,
    constraint fk2 foreign key (bossID) references boss (bossID)
);


CREATE TABLE IF NOT EXISTS transaction
(
    transactionID       serial unique,
    date                date,
    amount              double precision,
    ccNumber            bigint,
    ccNumberDestination bigint,
    description         varchar(40),
    constraint fk foreign key (ccNumber) references creditCard (ccNumber)
);
