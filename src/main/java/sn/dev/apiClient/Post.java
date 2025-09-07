package sn.dev.apiClient;
public record Post(
        Long id,
        String name,
        String username,
        String email,
        Address address
) {}
