# CPU_Schedulers_Simulator_Strategies

## Description

This Java program simulates various CPU scheduling algorithms, including:

1. Preemptive Shortest-Job First (SJF) Scheduling with context switching.
2. Round Robin (RR) with context switching.
3. Preemptive Priority Scheduling (with solving the starvation problem).
4. AG Scheduling:

   - Each process is provided a static time to execute called a quantum.
   - The process transitions through three scenarios:
      1. Running process uses all its quantum time and still has work to do (increases its Quantum time by two).
      2. Running process executes as non-preemptive Priority and doesn't use all its quantum time (increases its Quantum time by ceil(the remaining Quantum time / 2)).
      3. Running process executes as preemptive Shortest-Job First (SJF) and doesn't use all its quantum time (increases its Quantum time by the remaining Quantum time).
      4. Running process doesn't use all of its quantum time because it's no longer needed, and the job was completed (sets its quantum time to zero).

The program allows users to input process details, such as process name, arrival time, burst time, and priority. It calculates and outputs the execution order, waiting time, turnaround time, average waiting time, average turnaround time, and quantum time history for each process.

## Example

For the given example:

| Process | Burst time | Arrival time | Priority | Quantum |
|---------|------------|--------------|----------|---------|
| P1      | 17         | 0            | 4        | 7       |
| P2      | 6          | 2            | 7        | 9       |
| P3      | 11         | 5            | 3        | 4       |
| P4      | 4          | 15           | 6        | 6       |

The program will output:

- Processes execution order: P1, P2, P3, P2, P1, P3, P4, P3, P1, P4, P1
- Waiting Time for each process: 0, 4, 7, 9, 12, 15, 18, 20, 26, 33, 35, 38
- Turnaround Time for each process: 17, 10, 18, 12, 29, 26, 22, 23, 38, 25, 38
- Average Waiting Time: 15.45
- Average Turnaround Time: 22.82

## Program Input

- Number of processes
- Round robin Time Quantum
- Context switching

For each process, you need to input:

- Process Name
- Process Arrival Time
- Process Burst Time
- Process Priority

## Program Output

For each scheduler, the program outputs:

- Processes execution order
- Waiting Time for each process
- Turnaround Time for each process
- Average Waiting Time
- Average Turnaround Time
- Quantum time history for each process (AG Scheduling)

## Project Guidelines

- The assignment is submitted in a group of a maximum of 5 students and a minimum of 4 students.
- Late submission is not allowed.
