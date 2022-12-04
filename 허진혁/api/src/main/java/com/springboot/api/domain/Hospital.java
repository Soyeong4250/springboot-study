package com.springboot.api.domain;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "hospitals")
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id")
    private Integer id;

    @Column(name = "open_service_name")
    private String openServiceName;

    @Column(name = "open_local_government_code")
    @NotNull
    private String openLocalGovernmentCode;

    @Column(name = "management_number")
    private String managementNumber;

    @Column(name = "license_data")
    private LocalDateTime licenseDate;

    @Column(name = "business_state")
    private Integer businessState;

    @Column(name = "business_status_code")
    private Integer businessStatusCode;

    @Column(name = "phone")
    private String phone;

    @Column(name = "full_address")
    private String fullAddress;

    @Column(name = "road_name_address")
    private String roadNameAddress;

    @Column(name = "hospital_name")
    private String hospitalName;

    @Column(name = "business_type_name")
    private String businessTypeName;

    @Column(name = "healthcare_provider_count")
    private Integer healthcareProviderCount;

    @Column(name = "patient_room_count")
    private Integer patientRoomCount;

    @Column(name = "total_number_of_beds")
    private Integer totalNumberOfBeds;

    @Column(name = "total_area_size")
    private Float totalAreaSize;
}
