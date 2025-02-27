package br.com.foodwise.platform.infrastructure.rest.controller;

import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menuEntity.RegisterMenuRequest;
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

import java.util.List;

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
                                                                "message": "Invalid menuEntity data provided"
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
    @PostMapping
    ResponseEntity<MenuResponse> createMenu(@RequestBody @Valid RegisterMenuRequest request);

    @Operation(
            description = "Retrieve a Menu by its ID",
            summary = "Get a menuEntity by ID",
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
    @GetMapping("/{id}")
    ResponseEntity<MenuResponse> getMenuById(@PathVariable("id") @NotNull Long id);

    @Operation(
            description = "Retrieve all Menus for a Restaurant by name",
            summary = "Get all menus by restaurant name",
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
                            description = "Restaurant not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            """
                                                    {
                                                        "statusCode": 404,
                                                        "errors": [
                                                            {
                                                                "code": "RESTAURANT_NOT_FOUND",
                                                                "message": "Restaurant not found"
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
    @GetMapping("/restaurant/{name}")
    ResponseEntity<List<MenuResponse>> getMenusByRestaurantName(@PathVariable("name") String name);

    @Operation(
            description = "Retrieve all Menus",
            summary = "Get all menus",
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
    @GetMapping
    ResponseEntity<List<MenuResponse>> getAllMenus();

    @Operation(
            description = "Update a Menu",
            summary = "Update menuEntity data",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Updated successfully"),
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
    @PutMapping("/{id}")
    ResponseEntity<MenuResponse> updateMenu(@PathVariable("id") Long id, @RequestBody @Valid RegisterMenuRequest menuRequestDTO);

    @Operation(
            description = "Delete a Menu",
            summary = "Delete a menuEntity by ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Deleted successfully"),
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
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteMenu(@PathVariable("id") Long id);
}
