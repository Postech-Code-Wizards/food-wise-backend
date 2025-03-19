package br.com.foodwise.platform.infrastructure.rest.controller;

import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menu.item.RegisterMenuItemAvailable;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.menu.item.RegisterMenuItemRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.response.MenuItemResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface MenuItemApi {

    @Operation(
            description = "Create a new Menu Item",
            summary = "Menu item creation",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found menu",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            """
                                                    {
                                                        "statusCode": 404,
                                                        "errors": [
                                                            {
                                                                "code": "NOT_FOUND",
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
    @PostMapping
    ResponseEntity<MenuItemResponse> createMenuItem(@RequestBody @Valid RegisterMenuItemRequest menuItemRequestDTO);

    @Operation(
            description = "Retrieve a Menu Item by its ID",
            summary = "Get a menu item by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MenuItemResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Menu item not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            """
                                                    {
                                                        "statusCode": 404,
                                                        "errors": [
                                                            {
                                                                "code": "NOT_FOUND",
                                                                "message": "Menu item not found"
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
    ResponseEntity<MenuItemResponse> getMenuItemById(@PathVariable Long id);

    @Operation(
            description = "Retrieve a Menu Item by name",
            summary = "Get a menu item by name",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MenuItemResponse.class)
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
    @GetMapping("/item/{name}")
    ResponseEntity<List<MenuItemResponse>> getMenusItemsByItemName(@PathVariable String name);

    @Operation(
            description = "Retrieve all Menus Item",
            summary = "Get all menus item",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ok",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MenuItemResponse.class)
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
    ResponseEntity<List<MenuItemResponse>> getAllMenuItems();

    @Operation(
            description = "Update a Menu Item",
            summary = "Update menu item data",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Updated successfully"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Menu Item not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            """
                                                    {
                                                        "statusCode": 404,
                                                        "errors": [
                                                            {
                                                                "code": "MENU_NOT_FOUND",
                                                                "message": "Menu item not found"
                                                            }
                                                        ]
                                                    }
                                                    """
                                    )
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
    @PutMapping("/{id}")
    ResponseEntity<MenuItemResponse> updateMenuItem(@PathVariable Long id, @RequestBody @Valid RegisterMenuItemRequest menuItemRequestDTO);

    @Operation(
            description = "Update a available Menu Item",
            summary = "Update available menu item",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Updated successfully"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Menu Item not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            """
                                                    {
                                                        "statusCode": 404,
                                                        "errors": [
                                                            {
                                                                "code": "MENU_NOT_FOUND",
                                                                "message": "Menu item not found"
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
    @PutMapping("/available/{id}")
    ResponseEntity<MenuItemResponse> updateAvailableMenuItem(@PathVariable Long id, @RequestBody @Valid RegisterMenuItemAvailable available);
}
