### USER-SERVICE (MYSQL, SPRING SECURITY)
___

- <span style="font-size:1rem;">Based auth for UserDetails:</span>
```java
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return (authentication);
```
```json
{
    "authorities": [],
    "details": {
        "remoteAddress": "127.0.0.1",
        "sessionId": null
    },
    "authenticated": true,
    "principal": {
        "user_id": "01J647PCV59EATGVPKR85HRFC8",
        "username": "paung",
        "email": "paung@gmail.com",
        "password": "$2a$10$vNQU4kLUD8Gcf1B8ADKdt.7SpValpyyIABxQ0yQIjKraC0iGboBTS",
        "userProfile": {
            "userprofile_id": 2,
            "avatar": null,
            "bio": null,
            "media_id": null
        },
        "enabled": true,
        "authorities": [],
        "credentialsNonExpired": true,
        "accountNonExpired": true,
        "accountNonLocked": true
    },
    "credentials": null,
    "name": "paung"
}
```