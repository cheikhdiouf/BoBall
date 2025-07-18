package sn.atos.ProjetJava17.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sn.atos.ProjetJava17.dto.PlayerDto;

import java.util.List;

import static sn.atos.ProjetJava17.utils.Constants.APP_API_ROOT;

public interface PlayerController {

    @Operation(summary = "Save all player", description = "save a list of player", tags = "Players")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "save a plqyer", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PlayerDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Classe Not Found", content = @Content())
    })
    @PostMapping(value = APP_API_ROOT +"/player")
    ResponseEntity<PlayerDto> SavePlayer(@RequestBody PlayerDto playerDto);


/*

    @Operation(summary = "Get all country", description = "Get a list of classe", tags = "Countries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Get a list of country", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CountryDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Classe Not Found", content = @Content())
    })
    @GetMapping(value = APP_API_ROOT +"/countries")
    ResponseEntity<List<CountryDto>> findAllCountry();



    @Operation(summary = "update all country", description = "update country", tags = "Countries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "save a country", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CountryDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Classe Not Found", content = @Content())
    })
    @PutMapping(value = APP_API_ROOT +"/countries/{id}")
    ResponseEntity<CountryDto> updateCountry(@RequestBody CountryDto countryDto);


    @Operation(summary = "delete all country", description = "delete country", tags = "Countries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "save a country", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CountryDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Classe Not Found", content = @Content())
    })
    @DeleteMapping(value = APP_API_ROOT +"/countries/{id}")
    void deleteCountry(@PathVariable Long id);


    @Operation(summary = "delete all country", description = "getById country", tags = "Countries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "save a country", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CountryDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Classe Not Found", content = @Content())
    })
    @GetMapping(value = APP_API_ROOT +"/countries/{id}")
    ResponseEntity<CountryDto> getCountryById(@PathVariable Long id);

*/

}
