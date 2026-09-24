package com.example.studentfeedbackapp;

import java.util.Arrays;
import java.util.List;

public class SubjectRepository {

    public static List<String> getSubjectsWithFaculty(String department, String semester) {
        String safeDept = department != null ? department : "";
        String safeSem = semester != null ? semester : "";

        // Normalize inputs
        String deptKey = safeDept.trim().toLowerCase();
        String semKey = safeSem.trim().toLowerCase();

        // Standardize semester number (e.g. "semester 7" -> "7", "7" -> "7")
        String semNum;
        if (semKey.contains("1")) semNum = "1";
        else if (semKey.contains("2")) semNum = "2";
        else if (semKey.contains("3")) semNum = "3";
        else if (semKey.contains("4")) semNum = "4";
        else if (semKey.contains("5")) semNum = "5";
        else if (semKey.contains("6")) semNum = "6";
        else if (semKey.contains("7")) semNum = "7";
        else if (semKey.contains("8")) semNum = "8";
        else semNum = "1";

        // Check IoT / Industrial IoT Department
        if (deptKey.contains("iot") || deptKey.contains("internet of things") || deptKey.contains("industrial iot")) {
            return getIotSubjects(semNum);
        }

        // Check CSE
        if (deptKey.contains("computer science") || deptKey.contains("cse")) {
            return getCseSubjects(semNum);
        }

        // Check IT
        if (deptKey.contains("information technology") || deptKey.equals("it")) {
            return getItSubjects(semNum);
        }

        // Check Computer Technology
        if (deptKey.contains("computer technology")) {
            return getCtSubjects(semNum);
        }

        // Check AI & Data Science
        if (deptKey.contains("ai & data science") || deptKey.contains("aids") || deptKey.contains("data science")) {
            return getAidsSubjects(semNum);
        }

        // Check Robotics & AI
        if (deptKey.contains("robotics")) {
            return getRoboticsSubjects(semNum);
        }

        // Check ETC / Electronics
        if (deptKey.contains("electronics") || deptKey.contains("telecommunication") || deptKey.contains("etc")) {
            return getEtcSubjects(semNum);
        }

        // Check Electrical
        if (deptKey.contains("electrical")) {
            return getElectricalSubjects(semNum);
        }

        // Check Mechanical
        if (deptKey.contains("mechanical")) {
            return getMechanicalSubjects(semNum);
        }

        // Check Civil
        if (deptKey.contains("civil")) {
            return getCivilSubjects(semNum);
        }

        // Check Chemical
        if (deptKey.contains("chemical")) {
            return getChemicalSubjects(semNum);
        }

        // Check Aeronautical
        if (deptKey.contains("aeronautical")) {
            return getAeronauticalSubjects(semNum);
        }

        // Fallback for General / Default
        return getDefaultSubjects();
    }

    private static List<String> getIotSubjects(String sem) {
        switch (sem) {
            case "1":
                return Arrays.asList(
                    "Engineering Mathematics-I - Prof. A. Sharma",
                    "Applied Physics - Prof. B. Kulkarni",
                    "Basic Electrical Engineering - Prof. C. Deshmukh",
                    "Fundamentals of C & IoT - Prof. D. Patil",
                    "Engineering Graphics - Prof. E. Joshi"
                );
            case "2":
                return Arrays.asList(
                    "Engineering Mathematics-II - Prof. A. Sharma",
                    "Applied Chemistry - Prof. B. Kulkarni",
                    "Electronic Devices & Circuits - Prof. F. Shinde",
                    "Python Programming - Prof. G. Pawar",
                    "Engineering Mechanics - Prof. H. Kadam"
                );
            case "3":
                return Arrays.asList(
                    "Analog Electronics - Prof. R. Verma",
                    "Digital Logic Design - Prof. S. Mehta",
                    "Data Structures & Algorithms - Prof. T. Gupta",
                    "Sensors & Transducers - Prof. U. Rao",
                    "Object Oriented Programming - Prof. V. Nair"
                );
            case "4":
                return Arrays.asList(
                    "Microcontrollers & Embedded Systems - Prof. K. Bhatt",
                    "IoT Architecture & Protocols - Prof. L. Kulkarni",
                    "Signals & Systems - Prof. M. Singh",
                    "Database Management Systems - Prof. N. Roy",
                    "Discrete Mathematics - Prof. O. Pandita"
                );
            case "5":
                return Arrays.asList(
                    "Industrial Automation & PLCs - Prof. P. Saxena",
                    "Wireless Networks - Prof. Q. Contractor",
                    "Computer Networks - Prof. R. Thorat",
                    "Cloud Computing for IoT - Prof. S. Bansal",
                    "Operating Systems - Prof. T. Mahajan"
                );
            case "6":
                return Arrays.asList(
                    "Edge Computing & Fog Analytics - Prof. U. Wagh",
                    "Embedded Linux - Prof. V. Gadkari",
                    "IoT Security - Prof. W. Kulkarni",
                    "Machine Learning for IoT - Prof. X. Fernandez",
                    "Mobile App Development for IoT - Prof. Y. Jadhav"
                );
            case "7":
                // Exact semester 7 IoT subjects and faculty requested by user
                return Arrays.asList(
                    "MDWD - Prof. Pranali Nitnaware",
                    "DIOT - Prof. Mohini Kotamwar",
                    "Python for Data Science - Prof. Ankita Dhenge",
                    "WSN - Prof. Sharda Mam",
                    "Big Data Analytics - Prof. Priya Kambhale"
                );
            case "8":
                return Arrays.asList(
                    "Industrial Robotics - Prof. A. Chawla",
                    "Smart Cities & Infrastructure - Prof. B. More",
                    "Industry 4.0 & Smart Manufacturing - Prof. C. Vaidya",
                    "IoT Standards & Governance - Prof. D. Shinde",
                    "Major Project - Prof. E. Chaudhari"
                );
            default:
                return getDefaultSubjects();
        }
    }

    private static List<String> getCseSubjects(String sem) {
        switch (sem) {
            case "1":
                return Arrays.asList(
                    "Engineering Mathematics-I - Prof. A. Sharma",
                    "Engineering Physics - Prof. B. Kulkarni",
                    "Basic Electrical Engineering - Prof. C. Deshmukh",
                    "Programming for Problem Solving - Prof. D. Patil",
                    "Engineering Graphics - Prof. E. Joshi"
                );
            case "2":
                return Arrays.asList(
                    "Engineering Mathematics-II - Prof. A. Sharma",
                    "Engineering Chemistry - Prof. B. Kulkarni",
                    "Basic Electronics - Prof. F. Shinde",
                    "Object Oriented Programming - Prof. G. Pawar",
                    "Engineering Mechanics - Prof. H. Kadam"
                );
            case "3":
                return Arrays.asList(
                    "Data Structures & Algorithms - Prof. S. Kulkarni",
                    "Digital Logic & Design - Prof. M. Joshi",
                    "Discrete Mathematics - Prof. P. Deshmukh",
                    "Object Oriented Programming with Java - Prof. R. Patil",
                    "Computer Organization & Architecture - Prof. V. Shinde"
                );
            case "4":
                return Arrays.asList(
                    "Operating Systems - Prof. N. Kale",
                    "Database Management Systems - Prof. A. Bhatt",
                    "Theory of Computation - Prof. S. Mehta",
                    "Computer Networks - Prof. K. Rao",
                    "Design & Analysis of Algorithms - Prof. D. Kulkarni"
                );
            case "5":
                return Arrays.asList(
                    "Software Engineering - Prof. P. Thorat",
                    "Web Technologies - Prof. G. Verma",
                    "Computer Graphics - Prof. H. Pawar",
                    "Cyber Security - Prof. M. Kulkarni",
                    "Artificial Intelligence - Prof. S. Dhingra"
                );
            case "6":
                return Arrays.asList(
                    "Compiler Design - Prof. R. More",
                    "Cloud Computing - Prof. T. Jadhav",
                    "Machine Learning - Prof. N. Deshmukh",
                    "Information Security - Prof. A. Shinde",
                    "Mobile Application Development - Prof. P. Joshi"
                );
            case "7":
                return Arrays.asList(
                    "Deep Learning - Prof. S. Roy",
                    "Big Data Analytics - Prof. V. Kadam",
                    "Internet of Things - Prof. M. Shinde",
                    "Agile Software Development - Prof. R. Bansal",
                    "Blockchain Technology - Prof. K. Wagh"
                );
            case "8":
                return Arrays.asList(
                    "Distributed Systems - Prof. A. Kulkarni",
                    "Natural Language Processing - Prof. B. Deshmukh",
                    "Quantum Computing - Prof. C. Patil",
                    "Major Project & Seminar - Prof. D. Shinde"
                );
            default:
                return getDefaultSubjects();
        }
    }

    private static List<String> getItSubjects(String sem) {
        switch (sem) {
            case "1":
            case "2":
                return getCseSubjects(sem);
            case "3":
                return Arrays.asList(
                    "Data Structures - Prof. A. Joshi",
                    "Object Oriented Programming - Prof. R. Deshmukh",
                    "Digital Electronics - Prof. S. Kulkarni",
                    "Discrete Structure - Prof. P. Shinde",
                    "Principles of Management - Prof. M. Kale"
                );
            case "4":
                return Arrays.asList(
                    "Database Systems - Prof. N. Patil",
                    "Software Engineering - Prof. K. More",
                    "Computer Networks - Prof. V. Deshmukh",
                    "Operating Systems - Prof. S. Thorat",
                    "Web Programming - Prof. B. Jadhav"
                );
            case "5":
                return Arrays.asList(
                    "Web Technologies - Prof. M. Kulkarni",
                    "Information Security - Prof. R. Shinde",
                    "Cloud Computing - Prof. P. Bhatt",
                    "System Programming - Prof. D. Joshi",
                    "Unix & Linux Administration - Prof. S. Pawar"
                );
            case "6":
                return Arrays.asList(
                    "Wireless Networks - Prof. A. Kale",
                    "Data Mining & Warehousing - Prof. V. Patil",
                    "Mobile App Development - Prof. R. Deshmukh",
                    "Software Testing - Prof. N. Kulkarni",
                    "DevOps & CI/CD - Prof. S. More"
                );
            case "7":
                return Arrays.asList(
                    "Enterprise Resource Planning - Prof. P. Shinde",
                    "Information Retrieval - Prof. M. Joshi",
                    "Cyber Forensics - Prof. A. Thorat",
                    "Machine Learning - Prof. S. Kulkarni",
                    "Cloud Architecture - Prof. D. Patil"
                );
            case "8":
                return Arrays.asList(
                    "Soft Computing - Prof. R. Kale",
                    "Big Data Engineering - Prof. V. More",
                    "Network Security - Prof. N. Shinde",
                    "Major Project - Prof. S. Deshmukh"
                );
            default:
                return getDefaultSubjects();
        }
    }

    private static List<String> getCtSubjects(String sem) {
        switch (sem) {
            case "1":
            case "2":
                return getCseSubjects(sem);
            case "3":
                return Arrays.asList(
                    "Digital Circuits - Prof. D. Shinde",
                    "Object Oriented Modeling - Prof. P. Kulkarni",
                    "Data Structures - Prof. A. Joshi",
                    "Microprocessors - Prof. M. Thorat",
                    "Discrete Mathematics - Prof. S. Kale"
                );
            case "4":
                return Arrays.asList(
                    "System Programming - Prof. R. Deshmukh",
                    "Embedded Systems - Prof. K. Bhatt",
                    "Computer Networks - Prof. V. Patil",
                    "Operating Systems - Prof. N. Shinde",
                    "Database Management - Prof. S. Pawar"
                );
            case "5":
                return Arrays.asList(
                    "Computer Architecture - Prof. H. Pawar",
                    "Software Engineering - Prof. G. Verma",
                    "Real Time Operating Systems - Prof. U. Rao",
                    "Web Technology - Prof. M. Kulkarni",
                    "Signals & Systems - Prof. A. Sharma"
                );
            case "6":
                return Arrays.asList(
                    "VLSI Design - Prof. O. More",
                    "Network Security - Prof. R. Shinde",
                    "Compiler Construction - Prof. N. Deshmukh",
                    "Internet of Things - Prof. L. Kulkarni",
                    "Mobile Computing - Prof. P. Joshi"
                );
            case "7":
                return Arrays.asList(
                    "Advanced Computer Networks - Prof. S. Roy",
                    "Robotics & Automation - Prof. V. Kadam",
                    "High Performance Computing - Prof. M. Shinde",
                    "Cyber Security - Prof. R. Bansal",
                    "Elective-I - Prof. K. Wagh"
                );
            case "8":
                return Arrays.asList(
                    "Parallel Processing - Prof. A. Kulkarni",
                    "Cloud Security - Prof. B. Deshmukh",
                    "Major Project - Prof. D. Shinde"
                );
            default:
                return getDefaultSubjects();
        }
    }

    private static List<String> getAidsSubjects(String sem) {
        switch (sem) {
            case "1":
            case "2":
                return getCseSubjects(sem);
            case "3":
                return Arrays.asList(
                    "Python Programming - Prof. A. Kulkarni",
                    "Data Structures & Algorithms - Prof. B. Deshmukh",
                    "Mathematical Foundations for Data Science - Prof. C. Patil",
                    "Digital Electronics - Prof. D. Shinde",
                    "Database Systems - Prof. E. More"
                );
            case "4":
                return Arrays.asList(
                    "Machine Learning Fundamentals - Prof. F. Joshi",
                    "Statistical Methods for Data Science - Prof. G. Kale",
                    "Computer Networks - Prof. H. Pawar",
                    "Operating Systems - Prof. I. Kulkarni",
                    "Design & Analysis of Algorithms - Prof. J. Bhatt"
                );
            case "5":
                return Arrays.asList(
                    "Advanced Machine Learning - Prof. K. Thorat",
                    "Data Visualization & Storytelling - Prof. L. Shinde",
                    "Big Data Analytics - Prof. M. Deshmukh",
                    "Web Scraping & Data Mining - Prof. N. Patil",
                    "Neural Networks & Deep Learning - Prof. O. More"
                );
            case "6":
                return Arrays.asList(
                    "Natural Language Processing - Prof. P. Kulkarni",
                    "Computer Vision - Prof. Q. Joshi",
                    "Reinforcement Learning - Prof. R. Shinde",
                    "AI Ethics & Governance - Prof. S. Kale",
                    "MLOps - Prof. T. Deshmukh"
                );
            case "7":
                return Arrays.asList(
                    "Generative AI & LLMs - Prof. U. Pawar",
                    "Predictive Analytics - Prof. V. Kulkarni",
                    "AI in Healthcare & Finance - Prof. W. Shinde",
                    "Cognitive Systems - Prof. X. More",
                    "Time Series Forecasting - Prof. Y. Patil"
                );
            case "8":
                return Arrays.asList(
                    "Autonomous Systems - Prof. Z. Deshmukh",
                    "AI Strategy & Leadership - Prof. A. Joshi",
                    "Big Data Engineering - Prof. B. Shinde",
                    "Major Project - Prof. C. Kulkarni"
                );
            default:
                return getDefaultSubjects();
        }
    }

    private static List<String> getRoboticsSubjects(String sem) {
        switch (sem) {
            case "1":
            case "2":
                return getCseSubjects(sem);
            case "3":
                return Arrays.asList(
                    "Kinematics of Machinery - Prof. A. Deshmukh",
                    "Sensors & Actuators - Prof. B. Patil",
                    "Electronic Circuits - Prof. C. Kulkarni",
                    "Programming for Robotics - Prof. D. Shinde",
                    "Engineering Mathematics III - Prof. E. More"
                );
            case "4":
                return Arrays.asList(
                    "Robot Control Systems - Prof. F. Joshi",
                    "Microcontrollers for Robotics - Prof. G. Kale",
                    "Dynamics of Machines - Prof. H. Pawar",
                    "Data Structures in C++ - Prof. I. Kulkarni",
                    "Computer Aided Design - Prof. J. Bhatt"
                );
            case "5":
                return Arrays.asList(
                    "Robot Dynamics & Motion Planning - Prof. K. Thorat",
                    "Embedded Robotics - Prof. L. Shinde",
                    "Artificial Intelligence for Robots - Prof. M. Deshmukh",
                    "PLC & Industrial Automation - Prof. N. Patil",
                    "Computer Vision for Robotics - Prof. O. More"
                );
            case "6":
                return Arrays.asList(
                    "Autonomous Navigation & SLAM - Prof. P. Kulkarni",
                    "Human-Robot Interaction - Prof. Q. Joshi",
                    "Deep Learning for Robotics - Prof. R. Shinde",
                    "Mechatronic Systems - Prof. S. Kale",
                    "Robot Operating System (ROS) - Prof. T. Deshmukh"
                );
            case "7":
                return Arrays.asList(
                    "Aerial & Drones Robotics - Prof. U. Pawar",
                    "Swarm Robotics - Prof. V. Kulkarni",
                    "Industrial Robotics & Industry 4.0 - Prof. W. Shinde",
                    "Medical Robotics - Prof. X. More",
                    "Bio-inspired Robotics - Prof. Y. Patil"
                );
            case "8":
                return Arrays.asList(
                    "Space & Underwater Robotics - Prof. Z. Deshmukh",
                    "Robotic Perception - Prof. A. Joshi",
                    "Robotics System Integration - Prof. B. Shinde",
                    "Major Project - Prof. C. Kulkarni"
                );
            default:
                return getDefaultSubjects();
        }
    }

    private static List<String> getEtcSubjects(String sem) {
        switch (sem) {
            case "1":
            case "2":
                return getCseSubjects(sem);
            case "3":
                return Arrays.asList(
                    "Electronic Devices - Prof. A. Patil",
                    "Network Analysis & Synthesis - Prof. B. Kulkarni",
                    "Signals & Systems - Prof. C. Deshmukh",
                    "Digital Logic Design - Prof. D. Shinde",
                    "Engineering Mathematics III - Prof. E. Joshi"
                );
            case "4":
                return Arrays.asList(
                    "Analog Circuits - Prof. F. Kale",
                    "Analog Communication - Prof. G. More",
                    "Microprocessors & Peripherals - Prof. H. Thorat",
                    "Electromagnetic Fields & Waves - Prof. I. Pawar",
                    "Control Systems - Prof. J. Bhatt"
                );
            case "5":
                return Arrays.asList(
                    "Digital Communication - Prof. K. Shinde",
                    "Digital Signal Processing (DSP) - Prof. L. Kulkarni",
                    "Microcontrollers & Applications - Prof. M. Deshmukh",
                    "Antenna & Wave Propagation - Prof. N. Patil",
                    "VLSI Design - Prof. O. More"
                );
            case "6":
                return Arrays.asList(
                    "Optical Fiber Communication - Prof. P. Joshi",
                    "Wireless Communication - Prof. Q. Kale",
                    "Embedded Systems - Prof. R. Thorat",
                    "Microwave Engineering - Prof. S. Pawar",
                    "Power Electronics - Prof. T. Kulkarni"
                );
            case "7":
                return Arrays.asList(
                    "Cellular Networks & 5G - Prof. U. Shinde",
                    "Satellite Communication - Prof. V. Deshmukh",
                    "CMOS VLSI Design - Prof. W. Patil",
                    "Information Theory & Coding - Prof. X. More",
                    "IoT & Sensors Technology - Prof. Y. Joshi"
                );
            case "8":
                return Arrays.asList(
                    "Radar & Navigational Aids - Prof. Z. Kulkarni",
                    "Broadband Communication - Prof. A. Shinde",
                    "Robotics & Industrial Automation - Prof. B. Deshmukh",
                    "Major Project - Prof. C. Patil"
                );
            default:
                return getDefaultSubjects();
        }
    }

    private static List<String> getElectricalSubjects(String sem) {
        switch (sem) {
            case "1":
            case "2":
                return getCseSubjects(sem);
            case "3":
                return Arrays.asList(
                    "Electric Circuit Analysis - Prof. A. Deshmukh",
                    "Electrical Machines-I - Prof. B. Kulkarni",
                    "Analog Electronics - Prof. C. Patil",
                    "Electromagnetic Fields - Prof. D. Shinde",
                    "Engineering Mathematics III - Prof. E. More"
                );
            case "4":
                return Arrays.asList(
                    "Electrical Machines-II - Prof. F. Joshi",
                    "Power Systems-I - Prof. G. Kale",
                    "Control Systems - Prof. H. Pawar",
                    "Digital Electronics - Prof. I. Kulkarni",
                    "Electrical Measurements & Instrumentation - Prof. J. Bhatt"
                );
            case "5":
                return Arrays.asList(
                    "Power Electronics - Prof. K. Thorat",
                    "Power Systems-II - Prof. L. Shinde",
                    "Microprocessors & Microcontrollers - Prof. M. Deshmukh",
                    "High Voltage Engineering - Prof. N. Patil",
                    "Renewable Energy Systems - Prof. O. More"
                );
            case "6":
                return Arrays.asList(
                    "Switchgear & Protection - Prof. P. Kulkarni",
                    "Electric Drives & Control - Prof. Q. Joshi",
                    "Smart Grid Technology - Prof. R. Shinde",
                    "Power System Operation & Control - Prof. S. Kale",
                    "Industrial Automation - Prof. T. Deshmukh"
                );
            case "7":
                return Arrays.asList(
                    "Electric Vehicle Technology - Prof. U. Pawar",
                    "Energy Audit & Management - Prof. V. Kulkarni",
                    "Flexible AC Transmission Systems (FACTS) - Prof. W. Shinde",
                    "EHV AC/DC Transmission - Prof. X. More",
                    "Power Quality - Prof. Y. Patil"
                );
            case "8":
                return Arrays.asList(
                    "Microgrid Systems - Prof. Z. Deshmukh",
                    "Advanced Electric Drives - Prof. A. Joshi",
                    "Utilization of Electrical Energy - Prof. B. Shinde",
                    "Major Project - Prof. C. Kulkarni"
                );
            default:
                return getDefaultSubjects();
        }
    }

    private static List<String> getMechanicalSubjects(String sem) {
        switch (sem) {
            case "1":
            case "2":
                return getCseSubjects(sem);
            case "3":
                return Arrays.asList(
                    "Thermodynamics - Prof. A. Kulkarni",
                    "Engineering Metallurgy - Prof. B. Deshmukh",
                    "Strength of Materials - Prof. C. Patil",
                    "Manufacturing Processes-I - Prof. D. Shinde",
                    "Machine Drawing - Prof. E. More"
                );
            case "4":
                return Arrays.asList(
                    "Applied Thermodynamics - Prof. F. Joshi",
                    "Fluid Mechanics - Prof. G. Kale",
                    "Theory of Machines-I - Prof. H. Pawar",
                    "Manufacturing Processes-II - Prof. I. Kulkarni",
                    "Material Science - Prof. J. Bhatt"
                );
            case "5":
                return Arrays.asList(
                    "Heat Transfer - Prof. K. Thorat",
                    "Theory of Machines-II - Prof. L. Shinde",
                    "Design of Machine Elements - Prof. M. Deshmukh",
                    "Metrology & Quality Control - Prof. N. Patil",
                    "Hydraulics & Pneumatics - Prof. O. More"
                );
            case "6":
                return Arrays.asList(
                    "Internal Combustion Engines - Prof. P. Kulkarni",
                    "CAD / CAM - Prof. Q. Joshi",
                    "Operations Research - Prof. R. Shinde",
                    "Industrial Engineering - Prof. S. Kale",
                    "Refrigeration & Air Conditioning - Prof. T. Deshmukh"
                );
            case "7":
                return Arrays.asList(
                    "Automobile Engineering - Prof. U. Pawar",
                    "Finite Element Analysis - Prof. V. Kulkarni",
                    "Power Plant Engineering - Prof. W. Shinde",
                    "Robotics & Automation - Prof. X. More",
                    "Mechatronics - Prof. Y. Patil"
                );
            case "8":
                return Arrays.asList(
                    "Renewable Energy Engineering - Prof. Z. Deshmukh",
                    "Tribology - Prof. A. Joshi",
                    "Product Design & Development - Prof. B. Shinde",
                    "Major Project - Prof. C. Kulkarni"
                );
            default:
                return getDefaultSubjects();
        }
    }

    private static List<String> getCivilSubjects(String sem) {
        switch (sem) {
            case "1":
            case "2":
                return getCseSubjects(sem);
            case "3":
                return Arrays.asList(
                    "Surveying - Prof. A. Deshmukh",
                    "Mechanics of Structures - Prof. B. Kulkarni",
                    "Building Construction - Prof. C. Patil",
                    "Fluid Mechanics-I - Prof. D. Shinde",
                    "Engineering Geology - Prof. E. More"
                );
            case "4":
                return Arrays.asList(
                    "Advanced Surveying - Prof. F. Joshi",
                    "Structural Analysis-I - Prof. G. Kale",
                    "Concrete Technology - Prof. H. Pawar",
                    "Fluid Mechanics-II - Prof. I. Kulkarni",
                    "Building Planning & Drawing - Prof. J. Bhatt"
                );
            case "5":
                return Arrays.asList(
                    "Structural Analysis-II - Prof. K. Thorat",
                    "Geotechnical Engineering-I - Prof. L. Shinde",
                    "Transportation Engineering-I - Prof. M. Deshmukh",
                    "Environmental Engineering-I - Prof. N. Patil",
                    "Quantity Surveying & Estimation - Prof. O. More"
                );
            case "6":
                return Arrays.asList(
                    "Design of Steel Structures - Prof. P. Kulkarni",
                    "Geotechnical Engineering-II - Prof. Q. Joshi",
                    "Transportation Engineering-II - Prof. R. Shinde",
                    "Environmental Engineering-II - Prof. S. Kale",
                    "Hydrology & Water Resources - Prof. T. Deshmukh"
                );
            case "7":
                return Arrays.asList(
                    "Design of Reinforced Concrete Structures - Prof. U. Pawar",
                    "Construction Management - Prof. V. Kulkarni",
                    "Irrigation Engineering - Prof. W. Shinde",
                    "Earthquake Engineering - Prof. X. More",
                    "Town Planning & Architecture - Prof. Y. Patil"
                );
            case "8":
                return Arrays.asList(
                    "Bridge Engineering - Prof. Z. Deshmukh",
                    "Advanced Concrete Technology - Prof. A. Joshi",
                    "GIS & Remote Sensing - Prof. B. Shinde",
                    "Major Project - Prof. C. Kulkarni"
                );
            default:
                return getDefaultSubjects();
        }
    }

    private static List<String> getChemicalSubjects(String sem) {
        switch (sem) {
            case "1":
            case "2":
                return getCseSubjects(sem);
            case "3":
                return Arrays.asList(
                    "Chemical Process Calculations - Prof. A. Deshmukh",
                    "Fluid Flow Operations - Prof. B. Kulkarni",
                    "Organic Chemistry - Prof. C. Patil",
                    "Physical Chemistry - Prof. D. Shinde",
                    "Engineering Mathematics III - Prof. E. More"
                );
            case "4":
                return Arrays.asList(
                    "Heat Transfer Operations - Prof. F. Joshi",
                    "Chemical Engineering Thermodynamics - Prof. G. Kale",
                    "Mechanical Operations - Prof. H. Pawar",
                    "Materials Science for Chemical Engineers - Prof. I. Kulkarni",
                    "Applied Chemistry Lab - Prof. J. Bhatt"
                );
            case "5":
                return Arrays.asList(
                    "Mass Transfer Operations-I - Prof. K. Thorat",
                    "Chemical Reaction Engineering-I - Prof. L. Shinde",
                    "Process Instrumentation & Control - Prof. M. Deshmukh",
                    "Chemical Technology - Prof. N. Patil",
                    "Environmental Chemical Engineering - Prof. O. More"
                );
            case "6":
                return Arrays.asList(
                    "Mass Transfer Operations-II - Prof. P. Kulkarni",
                    "Chemical Reaction Engineering-II - Prof. Q. Joshi",
                    "Transport Phenomena - Prof. R. Shinde",
                    "Process Equipment Design - Prof. S. Kale",
                    "Plant Utilities & Safety - Prof. T. Deshmukh"
                );
            case "7":
                return Arrays.asList(
                    "Process Dynamics & Control - Prof. U. Pawar",
                    "Petrochemical Technology - Prof. V. Kulkarni",
                    "Polymer Technology - Prof. W. Shinde",
                    "Bioprocess Engineering - Prof. X. More",
                    "Chemical Engineering Economics - Prof. Y. Patil"
                );
            case "8":
                return Arrays.asList(
                    "Advanced Separation Techniques - Prof. Z. Deshmukh",
                    "Nanotechnology - Prof. A. Joshi",
                    "Major Project & Plant Design - Prof. B. Shinde"
                );
            default:
                return getDefaultSubjects();
        }
    }

    private static List<String> getAeronauticalSubjects(String sem) {
        switch (sem) {
            case "1":
            case "2":
                return getCseSubjects(sem);
            case "3":
                return Arrays.asList(
                    "Aerodynamics-I - Prof. A. Deshmukh",
                    "Aircraft Materials & Processes - Prof. B. Kulkarni",
                    "Thermodynamics for Aeronautics - Prof. C. Patil",
                    "Fluid Mechanics - Prof. D. Shinde",
                    "Engineering Mathematics III - Prof. E. More"
                );
            case "4":
                return Arrays.asList(
                    "Aerodynamics-II - Prof. F. Joshi",
                    "Aircraft Structures-I - Prof. G. Kale",
                    "Aircraft Propulsion-I - Prof. H. Pawar",
                    "Kinematics of Aerospace Machines - Prof. I. Kulkarni",
                    "Numerical Methods - Prof. J. Bhatt"
                );
            case "5":
                return Arrays.asList(
                    "Aircraft Structures-II - Prof. K. Thorat",
                    "Aircraft Propulsion-II - Prof. L. Shinde",
                    "Flight Mechanics-I - Prof. M. Deshmukh",
                    "Avionics & Flight Systems - Prof. N. Patil",
                    "Heat Transfer in Aerospace Systems - Prof. O. More"
                );
            case "6":
                return Arrays.asList(
                    "Flight Mechanics-II - Prof. P. Kulkarni",
                    "Computational Fluid Dynamics (CFD) - Prof. Q. Joshi",
                    "Aircraft Design & Maintenance - Prof. R. Shinde",
                    "Rocket & Space Propulsion - Prof. S. Kale",
                    "Aerospace Controls - Prof. T. Deshmukh"
                );
            case "7":
                return Arrays.asList(
                    "Helicopter Theory & Aerodynamics - Prof. U. Pawar",
                    "Spacecraft Dynamics & Control - Prof. V. Kulkarni",
                    "Air Transportation & Safety - Prof. W. Shinde",
                    "Composite Materials for Aerospace - Prof. X. More",
                    "Unmanned Aerial Vehicles (UAVs) - Prof. Y. Patil"
                );
            case "8":
                return Arrays.asList(
                    "Hypersonic Aerodynamics - Prof. Z. Deshmukh",
                    "Aeroelasticity - Prof. A. Joshi",
                    "Major Project - Prof. B. Shinde"
                );
            default:
                return getDefaultSubjects();
        }
    }

    private static List<String> getDefaultSubjects() {
        return Arrays.asList(
            "Core Engineering Subject 1 - Prof. A. Sharma",
            "Core Engineering Subject 2 - Prof. B. Kulkarni",
            "Advanced Technical Elective - Prof. C. Deshmukh",
            "Practical Lab Workshop - Prof. D. Patil",
            "Technical Seminar - Prof. E. Joshi"
        );
    }
}
