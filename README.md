# Netzplan

Network Plan Creation - Console Application

Project Description

This group project is a console application that allows PC users to enter data for a task list and generate a network plan from that data. The application calculates and visualizes all the relevant information needed to create a network plan. The functionality and project goals are derived from this:
Main Features:

    Task Data Entry: Users can enter all data for a task that cannot be automatically inferred from the context.

    Start Node: The first entered task is designated as the start node for the network plan.

    Task Dependencies: Each node can have up to three predecessor tasks. These dependencies are defined by the user.

    Network Plan Calculation: The application calculates all necessary information to generate a network plan from the entered data.

    Task Information Retrieval: Users can retrieve relevant task data, such as start and end times, duration, and other calculated information.

    Critical Path: The critical path of the project is retrievable by the user to determine the optimal project duration.

    Error Handling: The application validates inputs and displays error messages when incorrect data is entered.

    Edit Existing Tasks: Existing tasks can be modified, including changes to dependencies or other parameters.

Functionality

    Task Data Entry:

        The user enters all necessary task information, such as the duration and dependencies on other tasks.

        The first task is automatically set as the start node.

        For each subsequent task, dependencies are defined.

    Network Plan Calculation:

        The application automatically calculates the network plan based on the entered data, including all relevant time points and paths for the tasks and their dependencies.

    Data Retrieval:

        Users can retrieve information for any task, such as project duration, the critical path, and other calculated data.

    Error Handling:

        If an invalid input occurs (e.g., an incorrect dependency or invalid duration), the application will display an error message and prompt the user to correct the issue.

    Task Editing:

        Users can edit existing tasks and their dependencies at any time.

        
