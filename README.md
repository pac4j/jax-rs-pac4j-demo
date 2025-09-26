# jax-rs-pac4j-demo

A minimal JAX-RS (Jersey 3 on Grizzly) demo showcasing authentication with jax-rs-pac4j and pac4j:
- Indirect Basic Auth (IndirectBasicAuthClient)
- Form login (FormClient)
- CAS (CasClient)

It uses jax-rs-pac4j v7.0.1-SNAPSHOT and pac4j 6.x.

## Prerequisites
- JDK 17+
- Maven 3.8+
- curl (for the CAS test script)

## Build
```bash
mvn -q clean package
```

## Run
- Quick launcher (installs local jax-rs-pac4j if needed):
```bash
./run.sh
```
- Or run the fat JAR directly:
```bash
java -jar target/jax-rs-pac4j-demo-1.0.0-SNAPSHOT.jar
```
The server starts on http://localhost:8080

## Endpoints
- / — Home page with links
- /form/index — Protected by FormClient (use username = password)
- /basicauth/index — Protected by Indirect Basic Auth (use username = password)
- /cas/index — Protected by CAS (redirects to the demo CAS server)
- /protected/index — Generic protected page (any authenticated)
- /logout — Local logout via pac4j
