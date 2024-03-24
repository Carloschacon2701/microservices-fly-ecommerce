package org.carlos.flycore.Seed;


import lombok.RequiredArgsConstructor;
import org.carlos.flycore.Models.*;
import org.carlos.flycore.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class Seed {

    private final UserRepository userRepository;

    private final GenderRepository genderRepository;

    private final RoleRepository roleRepository;

    private final HotelRepository hotelRepository;

    private final RoomTypeRepository roomTypeRepository;

    private final RoomRepository roomRepository;

    private final CountryRepository countryRepository;

    private final CityRepository cityRepository;

    private final CommentRepository commentRepository;

    private final ReservationRepository reservationRepository;

    private final StatusRepository statusRepository;

    private final ReservationStatusRepository reservationStatusRepository;

    private final BranchRepository branchRepository;

    private final Map<Integer, Gender> genders = Map.of(
            1, Gender.builder()
                    .name("male")
                    .build(),
            2, Gender.builder()
                    .name("female")
                    .build()
    );

    private final Map<Integer, Role> roles = Map.of(
            1, Role.builder()
                    .name("admin")
                    .build(),
            2, Role.builder()
                    .name("client")
                    .build()
    );

    private final Map<Integer, User> users = Map.of(
            1, User.builder()
                    .email("Carloschacon@yopmail.com")
                    .username("Carlos")
                    .dateOfBirth(new Date())
                    .role(roles.get(1))
                    .gender(genders.get(1)).build(),

            2, User.builder()
                    .email("gabriela@yopmail.com")
                    .username("Gabriela")
                    .dateOfBirth(new Date())
                    .role(roles.get(2))
                    .gender(genders.get(2)).build()
    );

    private final Map<Integer, Country> countries = Map.of(
            1, Country.builder()
                    .name("Costa Rica")
                    .build(),
            2, Country.builder()
                    .name("Panama")
                    .build()
    );

    private final Map<Integer, City> cities = Map.of(
            1, City.builder()
                    .name("San Jose")
                    .country(countries.get(1))
                    .build(),
            2, City.builder()
                    .name("Panama City")
                    .country(countries.get(2))
                    .build()
    );

    private final Map<Integer, Branch> branches = Map.of(
            1, Branch.builder()
                    .name("Branch 1")
                    .description("Description 1")
                    .email("email@gmail.com")
                    .phone("12345678")
                    .build(),
            2, Branch.builder()
                    .name("Branch 2")
                    .description("Description 2")
                    .email("email2@gmail.com")
                    .phone("12345678")
                    .build()
    );

    private final Map<Integer, Hotel> hotels = Map.of(
            1, Hotel.builder()
                    .name("Hotel 1")
                    .address("Address 1")
                    .city(cities.get(1))
                    .address("Address 1")
                    .stars(5)
                    .branch(branches.get(1))
                    .phone("12345678")
                    .build(),
            2, Hotel.builder()
                    .name("Hotel 2")
                    .address("Address 2")
                    .city(cities.get(2))
                    .branch(branches.get(2))
                    .address("Address 2")
                    .stars(5)
                    .phone("12345678")
                    .build()
    );

    private final Map<Integer, RoomType> roomTypes = Map.of(
            1, RoomType.builder()
                    .name("Single")
                    .capacity(1)
                    .build(),
            2, RoomType.builder()
                    .name("Double")
                    .capacity(2)
                    .build()
    );

    private final Map<Integer, Room> rooms = Map.of(
            1, Room.builder()
                    .hotel(hotels.get(1))
                    .roomType(roomTypes.get(1))
                    .description("Room 1")
                    .quantity(10)
                    .price(100.0)
                    .build(),
            2, Room.builder()
                    .hotel(hotels.get(2))
                    .roomType(roomTypes.get(2))
                    .price(200.0)
                    .description("Room 2")
                    .quantity(10)
                    .build()
    );

    private final Map<Integer, Comment> comments = Map.of(
            1, Comment.builder()
                    .comment("Comment 1")
                    .rating(5)
                    .user(users.get(1))
                    .hotel(hotels.get(1))
                    .build(),
            2, Comment.builder()
                    .comment("Comment 2")
                    .rating(4)
                    .user(users.get(2))
                    .hotel(hotels.get(2))
                    .build()
    );

    private final Map<Integer, Status> status = Map.of(
            1, Status.builder()
                    .name("pending")
                    .build(),
            2, Status.builder()
                    .name("inactive")
                    .build(),
            3, Status.builder()
                    .name("paid")
                    .build()
    );

    private final Map<Integer, Reservation> reservations = Map.of(
            1, Reservation.builder()
                    .user(users.get(1))
                    .room(rooms.get(1))
                    .checkIn(new Date())
                    .checkOut(new Date())
                    .days(2)
                    .children(1)
                    .adults(2)
                    .build(),
            2, Reservation.builder()
                    .user(users.get(2))
                    .room(rooms.get(2))
                    .checkIn(new Date())
                    .checkOut(new Date())
                    .days(2)
                    .adults(2)
                    .build()
    );

    private final Map<Integer, ReservationStatus> reservationStatus = Map.of(
            1, ReservationStatus.builder()
                    .reservation(reservations.get(1))
                    .status(status.get(1))
                    .build(),
            2, ReservationStatus.builder()
                    .reservation(reservations.get(1))
                    .status(status.get(3))
                    .build(),
            3, ReservationStatus.builder()
                    .reservation(reservations.get(2))
                    .status(status.get(2))
                    .build()
    );


    @Bean
    CommandLineRunner seedData(){
        return args -> {
            System.out.println("Seeding data...");

            genders.forEach((id, gender) ->{
                genderRepository.save(gender);

                System.out.println("Gender " + gender.getName() + " saved");
            });

            roles.forEach((id, role) -> {
                roleRepository.save(role);
                System.out.println("Role " + role.getName() + " saved");
            });

            users.forEach((id, user) -> {
                userRepository.save(user);

                System.out.println("User " + user.getEmail() + " saved");
            });

            countries.forEach((id, country) -> {
                countryRepository.save(country);
                System.out.println("Country " + country.getName() + " saved");
            });

            cities.forEach((id, city) -> {
                cityRepository.save(city);
                System.out.println("City " + city.getName() + " saved");
            });

            branches.forEach((id, branch) -> {
                branchRepository.save(branch);
                System.out.println("Branch " + branch.getName() + " saved");
            });

            hotels.forEach((id, hotel) -> {
                hotelRepository.save(hotel);
                System.out.println("Hotel " + hotel.getName() + " saved");
            });

            roomTypes.forEach((id, roomType) -> {
                roomTypeRepository.save(roomType);
                System.out.println("RoomType " + roomType.getName() + " saved");
            });

            rooms.forEach((id, room) -> {
                roomRepository.save(room);
                System.out.println("Room " + room.getDescription() + " saved");
            });

            comments.forEach((id, comment) -> {
                commentRepository.save(comment);
                System.out.println("Comment " + comment.getComment() + " saved");
            });

            status.forEach((id, status) -> {
                statusRepository.save(status);
                System.out.println("Status " + status.getName() + " saved");
            });

            reservations.forEach((id, reservation) -> {
                reservationRepository.save(reservation);
                System.out.println("Reservation " + reservation.getId() + " saved");
            });

            reservationStatus.forEach((id, reservationStatus) -> {
                reservationStatusRepository.save(reservationStatus);
                System.out.println("ReservationStatus " + reservationStatus.getId() + " saved");
            });





            System.out.println("Data seeded");

        };
    }
}
