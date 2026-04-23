
# Smart Campus Sensor & Room Management API
![Java](https://img.shields.io/badge/Java-17%2B-blue?logo=java)
![JAX-RS](https://img.shields.io/badge/JAX--RS-3.1-green?logo=jakartaee)
![Maven](https://img.shields.io/badge/Maven-Build--Tool-C71A36?logo=apachemaven)

---

## Overview
This project was built as a part of the requirement for the Coursework submission for the Client-Server Architectures Module (5COSC022W), offered by the University of Westminster. It contains A RESTful API for managing rooms, sensors, and sensor readings for a university "Smart Campus".


---

## Video Demonstration
- [https://drive.google.com/file/d/1VjrhEHZSK8GupvYmLcJ4smYdEXY5N__f/view?usp=sharing](https://drive.google.com/file/d/1VjrhEHZSK8GupvYmLcJ4smYdEXY5N__f/view?usp=sharing)

---

## Tech Stack
- Java 17+
- JAX-RS (Jersey)
- Maven
- Servlet Container (Tomcat, Payara, GlassFish, etc.)
---

## Quickstart

1. Clone the repository:
   ```sh
   git clone https://github.com/Zilfaan/smart-campus.git
   cd smart-campus
   ```
2. Build the project with Maven:
   ```sh
   mvn clean package
   ```

3. Download and extract Apache Tomcat (if you don't have it):
   - Download from https://tomcat.apache.org/download-90.cgi
   - Extract the ZIP file (e.g., to `C:\tomcat`)

4. Deploy the WAR file to Tomcat using Command Prompt:
   ```cmd
   copy target\smart-campus-1.0-SNAPSHOT.war C:\tomcat\webapps\smart-campus.war
   ```

5. Start Tomcat (from the Tomcat bin directory):
   ```cmd
   cd C:\tomcat\bin
   startup.bat
   ```

6. Access the API in your browser or with curl:
   - http://localhost:8080/smart-campus/api/v1/

---

## API Endpoints

| Method | Endpoint                          | Description                       |
|--------|-----------------------------------|-----------------------------------|
| GET    | /api/v1/                         | Discovery & API info              |
| GET    | /api/v1/rooms                    | List all rooms                    |
| POST   | /api/v1/rooms                    | Create a new room                 |
| GET    | /api/v1/rooms/{id}               | Get room details                  |
| DELETE | /api/v1/rooms/{id}               | Delete a room (only if empty)     |
| GET    | /api/v1/sensors                  | List all sensors (filter by type) |
| POST   | /api/v1/sensors                  | Register a new sensor             |
| GET    | /api/v1/sensors/{id}/readings    | Get readings for a sensor         |
| POST   | /api/v1/sensors/{id}/readings    | Add a new reading                 |

---


## Sample curl Commands

### Discover API
```sh
curl http://localhost:8080/smart-campus/api/v1/
```

### List all rooms
```sh
curl http://localhost:8080/smart-campus/api/v1/rooms
```

### Create a new room
```sh
curl -X POST -H "Content-Type: application/json" -d '{"name":"Library Quiet Study","capacity":30}' http://localhost:8080/smart-campus/api/v1/rooms
```

### Get room details (replace ROOM_ID)
```sh
curl http://localhost:8080/smart-campus/api/v1/rooms/ROOM_ID
```

### Delete a room (replace ROOM_ID)
```sh
curl -X DELETE http://localhost:8080/smart-campus/api/v1/rooms/ROOM_ID
```

### List all sensors
```sh
curl http://localhost:8080/smart-campus/api/v1/sensors
```

### List sensors by type (replace TYPE)
```sh
curl http://localhost:8080/smart-campus/api/v1/sensors?type=TYPE
```

### Register a new sensor (replace ROOM_ID)
```sh
curl -X POST -H "Content-Type: application/json" -d '{"type":"Temperature","status":"ACTIVE","roomId":"ROOM_ID"}' http://localhost:8080/smart-campus/api/v1/sensors
```

### Get readings for a sensor (replace SENSOR_ID)
```sh
curl http://localhost:8080/smart-campus/api/v1/sensors/SENSOR_ID/readings
```

### Add a new reading (replace SENSOR_ID)
```sh
curl -X POST -H "Content-Type: application/json" -d '{"timestamp":1680000000000,"value":23.5}' http://localhost:8080/smart-campus/api/v1/sensors/SENSOR_ID/readings
```

---

## Coursework Report

### 1. Service Architecture & Setup

#### 1.1 Project & Application Configuration
**Question**: In your report, explain the default lifecycle of a JAX-RS Resource class. Is a
new instance instantiated for every incoming request, or does the runtime treat it as a
singleton? Elaborate on how this architectural decision impacts the way you manage and
synchronize your in-memory data structures (maps/lists) to prevent data loss or race conditions.

With JAX-RS, resource classes are request scoped by default, with each incoming HTTP request a new resource class. This prevents sharing resource instances between threads (meaning no unintended data will be shared) and makes it easier to ensure that resource classes can be safely accessed by multiple threads. Each request will only access its own resource instance, eliminating concurrency issues at the level of the resource class itself.

However, many in-memory data structures (e.g. lists or maps) that store Rooms, Sensors, and Sensor Readings, are available for concurrent access by all requests. Multiple threads may access shared data simultaneously, introducing the potential for race conditions if more than one thread tries to modify the same piece of data at the same time. To avoid race conditions between multiple threads accessing shared data, appropriate concurrency control mechanisms (e.g. thread-safe data structures, such as concurrent collections, using synchronization) will need to be implemented.

Additionally, operations that require multiple dependent operations (e.g. creating a sensor and associating it with a room) must be treated as atomic operations. If there is no proper coordination when interleaving the execution of two (or more) threads, then the system state could become inconsistent even if the individual operations being executed are thread-safe.

In conclusion, while the request-scoped lifecycle promotes resource design simplification (and therefore adheres to principles of statelessness), it also means that the maintenance of data consistency will be left to the management of data within shared data management and business logic supporting the resources managed by JAX-RS.

#### 1.2 The “Discovery” Endpoint
**Question**: Why is the provision of ”Hypermedia” (links and navigation within responses)
considered a hallmark of advanced RESTful design (HATEOAS)? How does this approach
benefit client developers compared to static documentation?

With the use of hypermedia (also known as Hypermedia as the Engine of Application State, or HATEOAS) in RESTful services, clients can dynamically determine which possible actions they can take against a RESTful service by following hyperlinks embedded in the responses they receive from the service. When using HATEOAS, clients no longer need to know the hard-coded endpoint addresses of the RESTful services they want to access.

For instance, a client receiving a response with room information can follow hyperlinks embedded in that response to retrieve the sensor resources associated with the room, create new sensors or delete existing sensors associated with the room. As a result, the client can easily navigate through related sensor resources without any prior knowledge of the URI structure that would have previously been necessary.

Rather than having the client's interactions with the server be dependent on a fixed endpoint or URI structure, the client can make use of the hypermedia links provided in each response to interact with the server. Therefore, when the server changes its URI structure, it does not affect the client (eg: Instead of calling create room endpoint directly, client calls the HATEOAS route and calls the route listed there). Another benefit of hypermedia responses is that they provide self-documentation for the client; the capabilities of the API are clearly defined in each response.

Hypermedia also makes it much easier to develop and test the client, since the client can begin from one endpoint and follow hypermedia links to perform multiple actions. In summary, HATEOAS enables a more flexible, maintainable and robust client/server architecture.

### 2. Room Management

#### 2.1 Room Resource Implementation
**Question**: When returning a list of rooms, what are the implications of returning only
IDs versus returning the full room objects? Consider network bandwidth and client side
processing.

When collection responses return only identifiers for items (e.g., room IDs), the amount of data sent over the network is reduced, which conserves bandwidth usage while enhancing performance (especially when dealing with very large datasets). The downside to this approach is that it increases client complexity because multiple requests must be made to retrieve all the details of items in the collection.

On the other hand, returning complete representations of items (e.g., details about every room) allows the client to receive all the requested information from a single response, but increases the amount of data transferred over the network and processing overhead on both the client and server sides for that same request.

In the end, the decision on which method to implement should be based on common usage patterns of the specific client and performance requirements of each method. 

#### 2.2 Room deletion & Safety logic
**Question**: Is the DELETE operation idempotent in your implementation? Provide a detailed
justification by describing what happens if a client mistakenly sends the exact same DELETE
request for a room multiple times.

Yes, the DELETE action is idempotent, as the effect on the state of the system is unaffected regardless of how many times the same DELETE request is made to it. After deleting a room, any subsequent DELETE requests for that same resource will result in a 404 Not Found status since there is no longer a resource to delete. While the response status for a DELETE request will change from a successful response to a 404 Not Found response, the state of the system will not change when the first request is processed, thus meeting the idempotent requirements.

Idempotency is especially valuable in distributed systems where clients may retry requests because of network issues. Idempotency ensures that repeating operations doesn't lead to unintended side effects.

The rule that prevents deletion from being successful on a room with assigned sensors helps maintain data integrity by preventing orphaned resources. Until all associated sensors are removed, any repeated requests to delete the room will result in the same error response, ensuring that the state of the system remains consistent.

### 3. Sensor Operations & Linking

#### 3.1 Sensor Resource & Integrity
**Question**: We explicitly use the @Consumes (MediaType.APPLICATION_JSON) annotation on
the POST method. Explain the technical consequences if a client attempts to send data in
a different format, such as text/plain or application/xml. How does JAX-RS handle this
mismatch?

The @Consumes(MediaType.APPLICATION_JSON) annotation restricts the endpoint to accept only JSON-formatted request bodies. If a client submits data in an unsupported format (e.g., text/plain or application/xml), JAX-RS is unable to locate a suitable MessageBodyReader to process the request.

In such cases, the framework automatically responds with a 415 Unsupported Media Type status. This mechanism enforces strict adherence to the API contract and ensures consistent input validation at the framework level.

By rejecting invalid formats early in the request lifecycle, unnecessary processing is avoided, and error handling becomes more predictable and standardized across endpoints.

#### 3.2 Filtered Retrieval & Search
**Question**: You implemented this filtering using @QueryParam. Contrast this with an alternative design where the type is part of the URL path (e.g., /api/vl/sensors/type/CO2). Why
is the query parameter approach generally considered superior for filtering and searching
collections?

The employ of query parameters (e.g., /sensors?type=CO2) to filter a resource is preferred, as the request still targets the same collection resource, while having additional selection criteria added to it.

Query parameters allow more flexibility with filtering, whereby many filters (e.g., type, status, roomId) can be used together without changing the underlying resource hierarchy, which promotes extensibility without requiring the definition of multiple path variations for various filter combinations.

Conversely, the use of filters embedded in the path (e.g., /sensors/type/CO2) indicates a hierarchical relationship instead of filtered view of the same resource, therefore reducing both clarity and scalability.

Query-based filtering is more consistent with REST principles and can be more easily integrated into caching, logging and analytics systems.

### 4. Deep Nesting with Sub-Resources

#### 4.1 Sub-resource Locator Pattern
**Question**: Discuss the architectural benefits of the Sub-Resource Locator pattern. How
does delegating logic to separate classes help manage complexity in large APIs compared
to defining every nested path (e.g., sensors/{id}/readings/{rid}) in one massive controller class?

A Sub-Resource Locator design pattern will delegate the way nested routes are handled to a specific resource class, as in the example of location "/sensors/{id}/readings" being managed through a separate resource class called "SensorReadingResource" rather than having both the Sensor and Reading sections of this resource class combined into one.

By separating concerns in this way, this method creates a modular product that separates the two different business processes of sensor management from sensor reading management, thus making both business processes less complex with respect to the class making each of them easier to maintain.

The hierarchy of the classes also reflects the inherent relationship between the two entities by relating readings with a specific sensor, therefore adhering to the logical structure of the domain and clarifying the purpose(s) of the API.

By separating the responsibilities associated with each component, developers can more easily test, extend, and modify individual parts of the API without affecting other parts of the system. This way, developers do not create large monolithic controllers, leading to an API that can be designed to scale.

### 5. Advanced Error Handling, Exception Mapping & Logging

#### 5.1 HTTP 422 vs 404
**Question**: Why is HTTP 422 often considered more semantically accurate than a standard
404 when the issue is a missing reference inside a valid JSON payload?

HTTP 422 (Unprocessable Entity) is more appropriate when a request is syntactically valid but semantically incorrect. For example, when a valid JSON payload references a non-existent roomId, the structure of the request is correct, but it violates domain constraints.

Returning a 404 (Not Found) would incorrectly imply that the endpoint itself is missing. In contrast, a 422 response clearly indicates that the request was understood but could not be processed due to invalid data.

Using precise status codes improves clarity and enables clients to implement more accurate error-handling logic.

#### 5.2 Stack trace exposure risks
**Question**: From a cybersecurity standpoint, explain the risks associated with exposing
internal Java stack traces to external API consumers. What specific information could an
attacker gather from such a trace?

Exposing internal stack traces to API consumers presents significant security risks. Stack traces may reveal sensitive implementation details, including class names, package structures, file paths, and library versions.

Such information can be exploited by attackers to identify vulnerabilities, understand system architecture, and craft targeted attacks. Even minor disclosures can assist in reconnaissance efforts.

A secure approach involves returning generic error messages (e.g., 500 Internal Server Error) to clients while logging detailed diagnostic information internally for developers. This ensures effective debugging without compromising system security.

#### 5.3 JAX-RS Filters for Logging
**Question**: Why is it advantageous to use JAX-RS filters for cross-cutting concerns like
logging, rather than manually inserting Logger.info() statements inside every single resource method?

JAX-RS filters provide a centralized mechanism for handling cross-cutting concerns such as logging. By implementing ContainerRequestFilter and ContainerResponseFilter, it is possible to capture and log request and response data across all endpoints consistently.

This approach eliminates the need for repetitive logging statements within individual resource methods, reducing code duplication and improving maintainability. Resource classes remain focused on core business logic, while logging is handled independently.

Centralized logging also enhances observability, simplifies debugging, and enables the integration of advanced features such as performance monitoring and request tracing. Overall, filters contribute to a cleaner, more modular, and maintainable system design.

---

README Last updated 23/04/2026
