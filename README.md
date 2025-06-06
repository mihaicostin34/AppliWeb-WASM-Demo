# AppliWeb-WASM-Demo

## Usage

0. Prerequisites: 
- install jdk 17
- run the following commands in a terminal session (the same session used to run tomcat, the database and maybe build the server)
```bash
export CATALINA_HOME=<path_to_apache_tomcat>
export JAVA_HOME=<path_to_jdk17>
export PATH=$JAVA_HOME/bin:$PATH
```

1. Run the database : 
```bash
java -cp hsqldb.jar org.hsqldb.server.Server --database.0 file:./data/xdb --dbname.0 xdb
```
Download the jar: https://sourceforge.net/projects/hsqldb/files/

2. Build and run the server: 
```bash
~/serveur$ ./mvnw clean spring-boot:run
```
To run the server, move the generated `.war` file into `/apache-tomcat-11.0.1/webapps` and start the tomcat server

---
**NOTE**

Building the server requires the db to be running. Why? I don't know?

---

3. Build and run the performance machine

Install rust (Linux, Mac, WSL): 
```bash
curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh
```

Run the machine:
```bash
~/machine$ cargo run
```

First compilation will take longer (because it needs to download and compile a lot of packages). Once it is done, you should see some output in the terminal. If the output says "Ok" you are good, if not call tech support (Mihai).

PLEASE the machine will only work if the server is running (otherwise it gets no response to its requests and thus dies).

---
**NOTE**

Apart from the machine logs that can indicate if things are running or not, you can also go to http://localhost:8080/serveur/listeinfo and see if there is any information stored in the db. 

---

4. Run the React client

Get `npm` (JS package manager):
```bash
# Download and install nvm:
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.40.3/install.sh | bash

# in lieu of restarting the shell
\. "$HOME/.nvm/nvm.sh"

# Download and install Node.js:
nvm install 22

# Verify the Node.js version:
node -v # Should print "v22.15.1".
nvm current # Should print "v22.15.1".

# Verify npm version:
npm -v # Should print "10.9.2".
```

Running the app: 
```bash
~/react-client$ npm start
```

If this does not work it may be because of missing packages, let me know. 

The react client should not be run without the server running. It will work, but no data will be shown.

## Future things to implement
- [ ] accounts with qualifiers like `User`, `Admin` that can only see certain computers
- [ ] historical analysis: querying over custom time ranges
- [ ] tagging computers: `dev`, `prod`, `test`
- [ ] add alerts to database

## Usage with codespaces

Codespace is setup to have the working environment already prepared, so no need to install anything on your machine, just to have an internet connection. The steps remain the same (BD, server, then machine and/or client).

The following have to be specified: 

1. `hsqldb` is located under `/`. Move to this directory before starting the database 
2. before starting the react client, run 

```bash
npm install
npm add react-router-dom
```

1. since the programs are all running on the same machine (the one executing the Docker container), no need to change any URLs/ports on the actual programs. For the react client, a window will automatically open and you can try it from there.

if you want to test just the server (using Postman or with `curl`) **DISCUSS WITH MIHAI FIRST** and then change the server's address in your prpgram (which should initially be `localhost:8080`) to the http link given by codespaces (under the `Ports` section in the terminal).

## Contributing

Regardless of how you write and run your code (locally or through codespace), please treat the repo accordingly: 
- make a separate branch when you are starting to implement a feature (and explicitly name said branch)
- use commit, push and whatever else you need (for your branch)
- DO NOT PUSH/MERGE DIRECTLY INTO MAIN because it will make me sad


## Pages
- `login.js`
- `create_account.js`
- `account.js`/`profile.js` 
  - shows data concerning the current user's profile (such as team, role)
- `dashboard.js`
  - main page, this appears when connected
  - shows overview/titlecards for available computers
  - quick stats/recent activity
  - logout button (just in case)
- `performance.js`
  - main stats page: shows detailed graph for the desired performance
  - allows filtering: 
    - by computer: see the data of the chosen computer
    - by time interval: see the data only in a certain interval
    - by parameter: choose what information to be shown
- `machine.js`
  - machine detail page: 
    - hostname, ip, os, other specs
    - last seen/status 
    - maybe some other generic/mocked info
- `/admin_pages/machine_management.js`
  - admin-only page: allows changing of machine data (such as assigned person, maybe make them go offline etc.)
- `/admin_pages/machine_management.js`
  - admin-only page: allows changing of user data (such as team or role)

### Page logic: 
- a user must first log in / create an account
- then they are taken to the main page (`dashboard.js`)
- from the main page they can go to `performance.js` to see some graphs or, for their accessible computer(s), see the associated `machine.js`
- admins will also have the option to go to their associated pages

## Business logic
- only the admins can create new accounts (we will manually add the admins in the db)
  - when creating a new account, the admin must indicate the username and password; usernames must be **unique**
- only a global admin can create **groups** / **teams**
- only a global admin can **modify** the parameters of other users: changing their associated group or role
- a **user** can have different **roles** on different **teams**
- a machine can be associated with **multiple** users and a user can use **multiple** machines
- a machine will **periodically** send its info to the server (Example: every 5 seconds)
  - this info will include the machine's **name**, **ip**, **performance information**, **timestamp**
- the app will allow a user to see information related to the computer(s) associated to them
  - for a **team leader/admin**, they will be able to see infos for all the computers of their team
  - for a **regular user**, they will only see the info associated to their username
  - for a **global admin**, they will be able to see everything
- the app will allow users to see logs associated to machines that are visible to them

**TO BE CONTINUED**

## Using the machine to fill the database
1. The machine will use two environment variables: `JOB` (corresponding to the desired data to add in the db) and `URL` (the url for the server). To run the program: 
```bash
> export JOB=<required_job> URL=<server_url> && cargo run
```
Leaving the `URL` option incomplete/not including it in the command will automatically set the used url to `http://localhost:8080`.

2. `JOB` options: 
    - Users: runs once, adds 4 users to the database 
    - Computers: runs once, adds 10 computers to the database
    - Groups: runs once, adds 6 groups to the database
    - Memberships: runs once, 6 memberships with the existing groups and users
    - Processes: runs once, adds processes to the database
    - Storages: runs once, adds storages 
    - ResourceUsages: runs endlessly on loop : first adds a few pre existing instances then every 5 seconds generates one more and sends it to the database
    - Sessions: runs once, adds sessions both ended (with end time) and ongoing (without end time)
3. Any data can be modified: the mock data is in lists inside the code. For each list of data I added a key to indicate what each member of the tuple means. 
