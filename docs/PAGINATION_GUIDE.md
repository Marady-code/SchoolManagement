# Pagination Implementation Guide

## Overview

This guide explains how pagination is implemented in the SchoolManagement system. Pagination allows you to break down large sets of data into smaller, manageable chunks (pages) and provides sorting capabilities.

## Core Components

### 1. PageRequest Class

```java
PageRequest pageRequest = PageRequest.of(page, size, sort);
```

- `page`: Zero-based page index (0 = first page)
- `size`: Number of items per page
- `sort`: Sorting configuration

### 2. PageResponse Class

```java
public class PageResponse<T> {
    private List<T> content;           // Actual data for current page
    private int pageNo;                // Current page number
    private int pageSize;              // Items per page
    private long totalElements;        // Total items across all pages
    private int totalPages;            // Total number of pages
    private boolean last;              // Whether this is the last page
}
```

## Implementation Examples

### 1. Controller Layer

```java
@GetMapping
public ResponseEntity<PageResponse<StudentResponseDTO>> getAllStudent(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "studentId") String sortBy,
        @RequestParam(defaultValue = "asc") String sortDir) {

    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
            Sort.by(sortBy).ascending() :
            Sort.by(sortBy).descending();

    PageRequest pageRequest = PageRequest.of(page, size, sort);
    return ResponseEntity.ok(studentService.getAllStudents(pageRequest));
}
```

### 2. Service Layer

```java
public PageResponse<StudentResponseDTO> getAllStudents(Pageable pageable) {
    Page<Student> studentPage = studentRepository.findAll(pageable);
    Page<StudentResponseDTO> dtoPage = studentPage.map(studentMapper::toResponseDTO);
    return PageResponse.of(dtoPage);
}
```

## API Usage

### Basic Request

```http
GET /students
```

Returns first 10 students, sorted by studentId ascending

### Custom Page Size

```http
GET /students?size=20
```

Returns 20 students per page

### Specific Page

```http
GET /students?page=2
```

Returns the third page (remember: page numbering starts at 0)

### Custom Sorting

```http
GET /students?sortBy=firstName&sortDir=desc
```

Returns students sorted by firstName in descending order

### Combined Parameters

```http
GET /students?page=1&size=15&sortBy=lastName&sortDir=asc
```

Returns the second page with 15 students per page, sorted by lastName ascending

## Response Format

```json
{
  "content": [
    {
      "studentId": 1,
      "firstName": "John",
      "lastName": "Doe"
      // ... other student fields
    }
    // ... more students
  ],
  "pageNo": 1,
  "pageSize": 15,
  "totalElements": 100,
  "totalPages": 7,
  "last": false
}
```

## Best Practices

1. **Default Values**

   - Always provide sensible default values for page parameters
   - Default page size: 10
   - Default sorting: by ID ascending

2. **Error Handling**

   ```java
   if (page < 0 || size < 1) {
       throw new IllegalArgumentException("Invalid page or size parameters");
   }
   ```

3. **Performance Considerations**

   - Use indexes for commonly sorted fields
   - Keep page sizes reasonable (10-50 items)
   - Consider caching for frequently accessed pages

4. **Security**
   - Implement maximum page size limits
   - Validate sort field names to prevent SQL injection

## Implementing Pagination in New Entities

1. **Update Repository**

   ```java
   public interface YourEntityRepository extends JpaRepository<YourEntity, Long> {
       // JpaRepository already includes pagination support
   }
   ```

2. **Update Service Interface**

   ```java
   public interface YourEntityService {
       PageResponse<YourEntityDTO> getAll(Pageable pageable);
   }
   ```

3. **Update Service Implementation**

   ```java
   @Override
   public PageResponse<YourEntityDTO> getAll(Pageable pageable) {
       Page<YourEntity> page = repository.findAll(pageable);
       return PageResponse.of(page.map(mapper::toDTO));
   }
   ```

4. **Add Controller Endpoint**
   ```java
   @GetMapping
   public ResponseEntity<PageResponse<YourEntityDTO>> getAll(
           @RequestParam(defaultValue = "0") int page,
           @RequestParam(defaultValue = "10") int size,
           @RequestParam(defaultValue = "id") String sortBy,
           @RequestParam(defaultValue = "asc") String sortDir) {
       Sort sort = Sort.by(sortDir.equalsIgnoreCase("asc") ?
               Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
       PageRequest pageRequest = PageRequest.of(page, size, sort);
       return ResponseEntity.ok(service.getAll(pageRequest));
   }
   ```

## Testing Pagination

1. **Test Different Page Sizes**

   ```http
   GET /students?size=5
   GET /students?size=20
   ```

2. **Test Page Navigation**

   ```http
   GET /students?page=0
   GET /students?page=1
   GET /students?page=2
   ```

3. **Test Sorting**

   ```http
   GET /students?sortBy=firstName&sortDir=asc
   GET /students?sortBy=lastName&sortDir=desc
   ```

4. **Test Edge Cases**
   - First page
   - Last page
   - Empty results
   - Invalid page numbers
   - Invalid sort fields

## Troubleshooting

1. **Invalid Page Number**

   - Symptoms: 400 Bad Request
   - Solution: Ensure page number ≥ 0

2. **Invalid Sort Field**

   - Symptoms: 500 Internal Server Error
   - Solution: Validate sort field names against entity properties

3. **Performance Issues**

   - Symptoms: Slow response times
   - Solution: Add database indexes for sorted fields

4. **Memory Issues**
   - Symptoms: OutOfMemoryError
   - Solution: Reduce page size or implement pagination at database level

## Additional Resources

- [Spring Data JPA Documentation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.paging-and-sorting)
- [Spring Pageable Interface](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Pageable.html)
- [Spring Page Interface](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Page.html)
