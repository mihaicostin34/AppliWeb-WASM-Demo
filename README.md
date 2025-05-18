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
~/serveur$ ./mvnw package
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
