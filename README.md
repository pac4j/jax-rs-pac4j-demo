# jax-rs-pac4j-demo

> This demo secures a JAX-RS application with **[jax-rs-pac4j](https://github.com/pac4j/jax-rs-pac4j)**, the JAX-RS implementation of **[pac4j](https://github.com/pac4j/pac4j)**, the security engine for Java.
> If it is useful to you, please ⭐ **[star pac4j on GitHub](https://github.com/pac4j/pac4j)**: it helps other developers discover it!

A minimal JAX-RS (Jersey 3 on Grizzly) demo showcasing authentication with jax-rs-pac4j and pac4j:
- Indirect Basic Auth (IndirectBasicAuthClient)
- Form login (FormClient)
- CAS (CasClient)

It uses jax-rs-pac4j v8.1 (`jersey3-pac4j`) and pac4j v6.x.

To secure a JAX-RS application with OpenID Connect, on Jersey 3 or 4, RESTEasy or Dropwizard, or to protect a REST API with bearer tokens, see the guide [How to secure a JAX-RS application with OIDC](https://www.pac4j.org/how-to-secure-a-jax-rs-application-with-oidc.html).

## Prerequisites
- JDK 17+
- Maven 3.8+
- curl (for the CAS test script)

## Build
```bash
mvn -q clean package
```

## Run
- Quick launcher (builds and starts the demo):
```bash
./run.sh
```
- Or run the fat JAR directly:
```bash
java -jar target/jax-rs-pac4j-demo-*.jar
```
The server starts on http://localhost:8080

## Endpoints
- / — Home page with links
- /form/index — Protected by FormClient (use username = password)
- /basicauth/index — Protected by Indirect Basic Auth (use username = password)
- /cas/index — Protected by CAS (redirects to the demo CAS server)
- /protected/index — Generic protected page (any authenticated)
- /logout — Local logout via pac4j
