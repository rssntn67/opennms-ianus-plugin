# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Build and install to local Maven repository
mvn clean install

# Run unit tests only (excludes *IT.java integration tests)
mvn test

# Run integration tests (runs *IT.java via the integration-test phase)
mvn verify

# Run a specific test class
mvn test -pl plugin -Dtest=AlarmForwarderTest

# Run a specific integration test class
mvn verify -pl plugin -Dit.test=AlarmForwarderIT
```

## Deploying to OpenNMS

**Via KAR file (preferred):**
```bash
cp assembly/kar/target/opennms-ianus-plugin-plugin.kar /opt/opennms/deploy/
# Then from Karaf shell:
feature:install opennms-plugins-ianus-plugin
```

**Via Maven feature repo (from Karaf shell):**
```bash
feature:repo-add mvn:org.opennms.plugins/karaf-features/0.1.0-SNAPSHOT/xml
feature:install opennms-plugins-ianus-plugin
```

**Live bundle reloading during development (from Karaf shell):**
```bash
bundle:watch *
```

REST endpoint is available at: `http://localhost:8980/opennms/rest/ianus-plugin/ping`

## Architecture

This is an **OSGi/Karaf plugin for OpenNMS** structured as a multi-module Maven project:

- **`plugin/`** — The OSGi bundle (`<packaging>bundle</packaging>`) containing all plugin logic.
- **`karaf-features/`** — Karaf features descriptor declaring bundle dependencies (okhttp, jackson, opennms-integration-api).
- **`assembly/kar/`** — Produces the `.kar` (Karaf ARchive) deployable artifact via `karaf-maven-plugin`.

### OSGi Wiring

Services are wired via Blueprint XML at `plugin/src/main/resources/OSGI-INF/blueprint/blueprint.xml`:

1. **`IanusPlugin`** — Implements `TimeSeriesStorage` from the OpenNMS Integration API. Exported as an OSGi service with `registration.export=true` so OpenNMS picks it up as the active time series backend.

2. **`WebhookHandlerImpl`** — Implements the JAX-RS interface `WebhookHandler` (`@Path("ianus-plugin")`). Registered with `application-path=/rest`, which causes OpenNMS to automatically mount it under the REST API.

### Configuration

Runtime config is read from `$OPENNMS_HOME/etc/org.opennms.plugins.ianus.cfg` via Blueprint `cm:property-placeholder` (persistent-id: `org.opennms.plugins.ianus`). Default property: `apiKey=TOKEN`.

### Custom Events

Two custom OpenNMS event UEIs are defined in `plugin/src/main/resources/events/plugin.ext.events.xml`:
- `uei.opennms.org/ianus-pluginPlugin/sendEventSuccessful` — alarm-type 2 (clear)
- `uei.opennms.org/ianus-pluginPlugin/sendEventFailed` — alarm-type 1 (problem)

### Test Conventions

- **Unit tests** (`*Test.java`): run during `mvn test`. Uses JUnit 4, Mockito, Hamcrest, Jackson + JSONAssert.
- **Integration tests** (`*IT.java`): run during `mvn verify`. Uses WireMock to mock HTTP endpoints and Awaitility for async assertions.
- The `AlarmForwarderIT` and `AlarmForwarderTest` reference `AlarmForwarder` and `ApiClient` classes — these are the primary classes to implement for alarm forwarding functionality.

### Key Dependencies

- **OpenNMS Integration API v2.0.0** — provides `TimeSeriesStorage`, `AlarmLifecycleListener`, `EventForwarder`, and immutable alarm model types.
- **OkHttp 4.10.0** — HTTP client for outbound REST calls (wrapped as OSGi bundle via ServiceMix).
- **Jackson 2.14.1** — JSON serialization.
- **Java 17**, **Karaf 4.3.10**.