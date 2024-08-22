--Coding
### Key Differences: `@RestControllerAdvice` vs. `@ControllerAdvice` and `@RestController` vs. `@Controller` from a `ResponseEntity` Perspective

#### 1. `@RestControllerAdvice` vs. `@ControllerAdvice`:

##### **`@ControllerAdvice`:**
- **Usage**: This annotation is used for handling exceptions globally across the application for controllers annotated with `@Controller`. It can be used to handle web-related exceptions and return either a `ModelAndView` (used for returning views in traditional MVC applications) or a `ResponseEntity` (for REST APIs).
- **Return Type**: By default, it returns views (i.e., `ModelAndView`), but it can also return `ResponseEntity` when handling exceptions for REST controllers if explicitly configured.
  
```java
@ControllerAdvice
public class GlobalControllerAdvice {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception ex) {
        ApiErrorResponse response = new ApiErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "An error occurred",
            Instant.now().toString(),
            Collections.singletonList(ex.getMessage())
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

##### **`@RestControllerAdvice`:**
- **Usage**: This annotation is specialized for REST APIs and is effectively the combination of `@ControllerAdvice` and `@ResponseBody`. It is designed to handle exceptions globally and return JSON/XML (depending on your configuration) for REST controllers annotated with `@RestController`.
- **Return Type**: It inherently returns the response as `ResponseEntity` or any other serialized format like JSON or XML because of the `@ResponseBody` aspect.

```java
@RestControllerAdvice
public class GlobalRestControllerAdvice {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception ex) {
        ApiErrorResponse response = new ApiErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "An error occurred",
            Instant.now().toString(),
            Collections.singletonList(ex.getMessage())
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

##### Summary:
- **`@ControllerAdvice`**: More generic; used for handling exceptions and returning either views (`ModelAndView`) or JSON/XML (when combined with `@ResponseBody`).
- **`@RestControllerAdvice`**: Specialized for REST APIs; handles exceptions and always returns JSON/XML responses.

#### 2. `@RestController` vs. `@Controller`:

##### **`@Controller`:**
- **Usage**: This annotation is used in traditional Spring MVC applications. It is typically used for web applications that return views (HTML, JSP, etc.). When you return an object, Spring will resolve it to a `ModelAndView` by default unless you explicitly use `@ResponseBody`.
- **Return Type**: The return type is typically a `ModelAndView`, but it can also return a `ResponseEntity` if `@ResponseBody` is used, which forces the response to be serialized into JSON/XML instead of a view.

```java
@Controller
public class MyWebController {

    @GetMapping("/web")
    public ModelAndView getWebPage() {
        return new ModelAndView("webPageView"); // Returns a view
    }

    @GetMapping("/json")
    @ResponseBody
    public ResponseEntity<String> getJson() {
        return new ResponseEntity<>("Hello JSON", HttpStatus.OK); // Returns JSON
    }
}
```

##### **`@RestController`:**
- **Usage**: This annotation is specifically designed for REST APIs. It is a shorthand for `@Controller` and `@ResponseBody`, meaning all methods in a class annotated with `@RestController` automatically serialize return values to JSON or XML.
- **Return Type**: The return type is typically `ResponseEntity` or other serialized objects (e.g., JSON or XML) by default.

```java
@RestController
public class MyRestController {

    @GetMapping("/json")
    public ResponseEntity<String> getJson() {
        return new ResponseEntity<>("Hello JSON", HttpStatus.OK); // Returns JSON
    }
}
```

##### Summary:
- **`@Controller`**: Used for handling traditional web requests, returning views by default, or `ResponseEntity` with `@ResponseBody` for REST responses.
- **`@RestController`**: Specialized for REST APIs; returns JSON/XML by default, eliminating the need to use `@ResponseBody`.

### Key Differences from `ResponseEntity` Perspective:
- **`@RestControllerAdvice` and `@RestController`** are specialized for REST APIs and automatically serialize responses (including `ResponseEntity` objects) into JSON or XML without needing to specify `@ResponseBody`.
- **`@ControllerAdvice` and `@Controller`** are used in both traditional MVC and REST APIs. However, in the context of REST, you would need to use `@ResponseBody` or manually return `ResponseEntity` to ensure a JSON/XML response instead of a view.
