package br.com.foodwise.platform.infrastructure.rest.controller;

import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menu.RegisterMenuRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.update.UpdateMenuRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.MenuResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface MenuApi {

    @Operation(
            description = "Create a new Menu",
            summary = "Menu creation",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid data",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            """
                                                    {
                                                        "statusCode": 400,
                                                        "errors": [
                                                            {
                                                                "code": "INVALID_MENU_DATA",
                                                                "message": "Invalid menu data provided"
                                                            }
                                                        ]
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            """
                                                    {
                                                        "statusCode": 500,
                                                        "errors": [
                                                            {
                                                                "code": "INTERNAL_SERVER_ERROR",
                                                                "message": "Internal server error"
                                                            }
                                                        ]
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    @PostMapping("/menu")
    ResponseEntity<Void> createMenu(@RequestBody @Valid RegisterMenuRequest request);

    @Operation(
            description = "Retrieve a Menu by its ID",
            summary = "Get a menu by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MenuResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Menu not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            """
                                                    {
                                                        "statusCode": 404,
                                                        "errors": [
                                                            {
                                                                "code": "MENU_NOT_FOUND",
                                                                "message": "Menu not found"
                                                            }
                                                        ]
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            """
                                                    {
                                                        "statusCode": 500,
                                                        "errors": [
                                                            {
                                                                "code": "INTERNAL_SERVER_ERROR",
                                                                "message": "Internal server error"
                                                            }
                                                        ]
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    @GetMapping("/menu/{id}")
    ResponseEntity<MenuResponse> getMenuById(@PathVariable("id") @NotNull Long id);

    @Operation(
            description = "Update a Menu's data",
            summary = "Update menu information",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No content"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Menu not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            """
                                                    {
                                                        "statusCode": 404,
                                                        "errors": [
                                                            {
                                                                "code": "MENU_NOT_FOUND",
                                                                "message": "Menu not found"
                                                            }
                                                        ]
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            """
                                                    {
                                                        "statusCode": 500,
                                                        "errors": [
                                                            {
                                                                "code": "INTERNAL_SERVER_ERROR",
                                                                "message": "Internal server error"
                                                            }
                                                        ]
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    @PutMapping("/menu/{id}")
    ResponseEntity<Void> updateMenu(
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateMenuRequest updateMenuRequest
    );

    @Operation(
            description = "Delete a Menu",
            summary = "Delete a menu by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Menu not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            """
                                                    {
                                                        "statusCode": 404,
                                                        "errors": [
                                                            {
                                                                "code": "MENU_NOT_FOUND",
                                                                "message": "Menu not found"
                                                            }
                                                        ]
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            """
                                                    {
                                                        "statusCode": 500,
                                                        "errors": [
                                                            {
                                                                "code": "INTERNAL_SERVER_ERROR",
                                                                "message": "Internal server error"
                                                            }
                                                        ]
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    @DeleteMapping("/menu/{id}")
    ResponseEntity<Void> deleteMenu(@PathVariable("id") @NotNull Long id);
}