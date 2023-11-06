package edu.trinity.got;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryMemberDAO implements MemberDAO {
    private final Collection<Member> allMembers =
            MemberDB.getInstance().getAllMembers();

    @Override
    public Optional<Member> findById(Long id) {
        return allMembers.stream()
                .filter(member -> member.id().equals(id))
                .findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return allMembers.stream()
                .filter(member -> member.name().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAllByHouse(House house) {
        return allMembers.stream()
                .filter(member -> member.house().equals(house))
                .toList();
    }

    @Override
    public Collection<Member> getAll() {
        return allMembers;
    }

    /**
     * Find all members whose name starts with S and sort by id (natural sort)
     */
    @Override
    public List<Member> startWithSandSortAlphabetically() {
        return allMembers.stream()
                .filter(member -> member.name().startsWith("S"))
                .sorted()
                .toList();
    }

    /**
     * Final all Lannisters and sort them by name
     */
    @Override
    public List<Member> lannisters_alphabeticallyByName() {
        return allMembers.stream()
                .filter(member -> member.house().equals(House.LANNISTER))
                .sorted(Comparator.comparing(Member::name))
                .toList();
    }

    /**
     * Find all members whose salary is less than the given value and sort by house
     */
    @Override
    public List<Member> salaryLessThanAndSortByHouse(double max) {
        return allMembers.stream()
                .filter(member -> member.salary() < max)
                .sorted(Comparator.comparing(Member::house))
                .toList();
    }

    /**
     * Sort members by House, then by name
     */
    @Override
    public List<Member> sortByHouseNameThenSortByNameDesc() {
        return allMembers.stream()
                .sorted(Comparator.comparing(Member::house)
                        .thenComparing(Member::name))
                .toList();
    }

    /**
     * Sort the members of a given House by birthdate
     */
    @Override
    public List<Member> houseByDob(House house) {
        return allMembers.stream()
                .filter(member -> member.house().equals(house))
                .sorted(Comparator.comparing(Member::dob))
                .toList();
    }

    /**
     * Find all Kings and sort by name in descending order
     */
    @Override
    public List<Member> kingsByNameDesc() {
        return allMembers.stream()
                .filter(member -> member.title().equals(Title.KING))
                .sorted(Comparator.comparing(Member::name).reversed())
                .toList();
    }

    /**
     * Find the average salary of all the members
     */
    @Override
    public double averageSalary() {
        return allMembers.stream()
                .mapToDouble(member -> member.salary())
                .summaryStatistics()
                .getAverage();
    }

    /**
     * Get the names of a given house, sorted in natural order
     * (note sort by _names_, not members)
     */
    @Override
    public List<String> namesSorted(House house) {
        return allMembers.stream()
                .filter(member -> member.house().equals(house))
                .map(Member::name)
                .sorted()
                .toList();
    }

    /**
     * Are any of the salaries greater than 100K?
     */
    @Override
    public boolean salariesGreaterThan(double max) {
        List<Member> salary = allMembers.stream()
                .filter(member -> member.salary() > 100000)
                .toList();
        if (!salary.isEmpty()) return true;
        return false;
    }

    /**
     * Are there any members of given house?
     */
    @Override
    public boolean anyMembers(House house) {
        List<Member> memberInHouse = allMembers.stream()
                .filter(member -> member.house().equals(house))
                .toList();
        if (!memberInHouse.isEmpty()) return true;
        return false;
    }

    /**
     * How many members of a given house are there?
     */
    @Override
    public long howMany(House house) {
        return allMembers.stream()
                .filter(member -> member.house().equals(house))
                .count();
    }

    /**
     * Return the names of a given house as a comma-separated string
     */
    @Override
    public String houseMemberNames(House house) {
        return allMembers.stream()
                .filter(member -> member.house().equals(house))
                .map(Member::name)
                .collect(Collectors.joining(", "));
    }

    /**
     * Who has the highest salary?
     */
    @Override
    public Optional<Member> highestSalary() {
        return allMembers.stream()
                .sorted(Comparator.comparing(Member::salary).reversed())
                .findFirst();
    }

    /**
     * Partition members into royalty and non-royalty
     * (note: royalty are KINGs and QUEENs only)
     */
    @Override
    public Map<Boolean, List<Member>> royaltyPartition() {
        return allMembers.stream()
                .collect(Collectors.partitioningBy(member -> member.title().equals(Title.KING) || member.title().equals(Title.QUEEN)));
    }

    /**
     * Group members into Houses
     */
    @Override
    public Map<House, List<Member>> membersByHouse() {
        return allMembers.stream()
                .collect(Collectors.groupingBy(Member::house));
    }

    /**
     * How many members are in each house?
     * (group by house, downstream collector using counting
     */
    @Override
    public Map<House, Long> numberOfMembersByHouse() {
        return allMembers.stream()
                .collect(Collectors.groupingBy(Member::house, Collectors.counting()));
    }

    /**
     * Get the max, min, and ave salary for each house
     */
    @Override
    public Map<House, DoubleSummaryStatistics> houseStats() {
        return allMembers.stream()
                .collect(Collectors.groupingBy(Member::house, Collectors.summarizingDouble(Member::salary)));
    }

    /**
     * Find all members in the given House that were born before 1990 and sort them by birthdate
     */
    @Override
    public List<Member> memberBornBefore(House house) {
        return allMembers.stream()
                .filter(member -> member.house().equals(house))
                .filter(member -> member.dob().getYear() < 1990)
                .sorted(Comparator.comparing(Member::dob))
                .toList();
    }

}
