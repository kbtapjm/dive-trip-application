package io.divetrip.api.v1;

import io.divetrip.dto.PageDto;
import io.divetrip.dto.SearchDto;
import io.divetrip.dto.request.DiverRequestDto;
import io.divetrip.service.DiverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class DiverApiController {

    private final DiverService diverService;

    @PostMapping(value = "/divers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDiver(@Valid @RequestBody DiverRequestDto.CreateDiver dto) {
        String diverId = diverService.createDiver(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{diverId}")
                .buildAndExpand(diverId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     *
     * {
     *   "content": [
     *     {
     *       "diverId": "88413517-dc4d-4fe9-a5ed-b119e579a1e3",
     *       "email": "tapjm@naver.com",
     *       "password": "$2a$10$Ua8.RZGziIHGzcbJ56yItepJPfDW3Jiy1Yx3.6tNR/cPmseltENom",
     *       "familyName": "park",
     *       "givenName": "jaemyung",
     *       "gender": "MALE",
     *       "birthday": "1982-05-09",
     *       "nationality": null,
     *       "countryCode": null,
     *       "contactNumber": null,
     *       "passportNo": "M80120775",
     *       "passportExpiryDate": "2027-05-04",
     *       "licensed": true,
     *       "createdBy": "tapjm@naver.com",
     *       "createdAt": "2024-01-21T10:09:10.668875",
     *       "updatedBy": null,
     *       "updateAt": null
     *     },
     *     {
     *       "diverId": "71732250-e4ea-44df-bbab-91a3d799b7d4",
     *       "email": "tapjmj@naver.com",
     *       "password": "$2a$10$IqHmIZT28M13ZgbjK6UQy.r3l1xZbQETetnk3.1UX4bxpW//FD1S6",
     *       "familyName": "박",
     *       "givenName": "재명2",
     *       "gender": "MALE",
     *       "birthday": "1982-05-09",
     *       "nationality": null,
     *       "countryCode": null,
     *       "contactNumber": null,
     *       "passportNo": "M80120775",
     *       "passportExpiryDate": "2027-05-04",
     *       "licensed": false,
     *       "createdBy": "tapjmj@naver.com",
     *       "createdAt": "2023-12-06T23:32:11.774969",
     *       "updatedBy": null,
     *       "updateAt": null
     *     },
     *     {
     *       "diverId": "ff831f32-8a38-463f-8bef-efa519b54a50",
     *       "email": "kbtapjm@gmail.com",
     *       "password": "$2a$10$oHHbNL6d7oM51JW1DL/Bo.l6hYDnVx7rzNuoQFe8ZklStcSvXw1k2",
     *       "familyName": "박",
     *       "givenName": "재명",
     *       "gender": "MALE",
     *       "birthday": "1982-05-09",
     *       "nationality": null,
     *       "countryCode": null,
     *       "contactNumber": null,
     *       "passportNo": "M80120775",
     *       "passportExpiryDate": "2027-05-04",
     *       "licensed": true,
     *       "createdBy": "kbtapjm@gmail.com",
     *       "createdAt": "2023-12-02T22:39:24.057425",
     *       "updatedBy": "kbtapjm@gmail.com",
     *       "updateAt": "2023-12-05T22:57:22.587184"
     *     }
     *   ],
     *   "pageable": {
     *     "pageNumber": 0,
     *     "pageSize": 10,
     *     "sort": {
     *       "empty": false,
     *       "sorted": true,
     *       "unsorted": false
     *     },
     *     "offset": 0,
     *     "paged": true,
     *     "unpaged": false
     *   },
     *   "last": true,
     *   "totalPages": 1,
     *   "totalElements": 3,
     *   "size": 10,
     *   "number": 0,
     *   "sort": {
     *     "empty": false,
     *     "sorted": true,
     *     "unsorted": false
     *   },
     *   "first": true,
     *   "numberOfElements": 3,
     *   "empty": false
     * }
     *
     * @param sort
     * @param orderBy
     * @param q
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/divers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDivers(
            @RequestParam(value = "sort", required = false, defaultValue = "") String sort,
            @RequestParam(value = "orderBy", required = false, defaultValue = "") String orderBy,
            @RequestParam(value = "q", required = false, defaultValue = "") String q,
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        PageDto pageDto = PageDto.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .build();

        // TODO: 정렬 대상이 컬럼에 없는경우 에러가 발생함, 그래서 정렬 타겟도 강제적으로 값을 ENUM으로 지정해서 불필요한 값이 넘어 오지 않게 처리 필요
        SearchDto searchDto = SearchDto.builder()
                .sort(sort)
                .orderBy(orderBy)
                .q(q)
                .build();

        return ResponseEntity.ok(diverService.getDiversAll(pageDto, searchDto));
    }

    @GetMapping(value = "/divers/{diverId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getDiver(@PathVariable UUID diverId) {
        return ResponseEntity.ok(diverService.getDiver(diverId));
    }

    @PutMapping(value = "/divers/{diverId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDiver(@PathVariable UUID diverId, @Valid @RequestBody DiverRequestDto.UpdateDiver dto) {
        diverService.updateDiver(diverId, dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/divers/{diverId}")
    public ResponseEntity<?> deleteDiver(@PathVariable UUID diverId) {
        diverService.deleteDiver(diverId);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/divers/{diverId}/password", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDiverPassword(@PathVariable UUID diverId, @Valid @RequestBody DiverRequestDto.UpdatePassword dto) {
        diverService.updateDiverPassword(diverId, dto);

        return ResponseEntity.ok().build();
    }

}
