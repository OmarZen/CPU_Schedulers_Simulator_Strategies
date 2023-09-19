import java.util.*;

public class RR {
	int Quantum;
	int NumOfProcesses, context_Switching;

	ArrayList<Process> processesList = new ArrayList<Process>(); /// Info table of the processes
	ArrayList<Process> excudeOrder = new ArrayList<Process>(); /// execution queue, (simulating Gantt chart)
	Queue<Process> RRqueue = new LinkedList<Process>();
	ArrayList<Integer> endTime = new ArrayList<Integer>();
	double averageWaitingTime = 0.0;
	double averageTurnAroundTime = 0.0;
	int timer = 0;

	public static class sortByArrivalTime implements Comparator<Process> {

		@Override
		public int compare(Process o1, Process o2) { /// If arrival times are equal, favor queue 1 process
//            if(o1.arrivalTime - o2.arrivalTime == 0){
//                return o1.queueNumber - o2.queueNumber;
//            }
			return o1.arrivalTime - o2.arrivalTime;

		}
	}

	void GetData() { // Take processes, arranges them in queue according to their arrival time.
		Scanner input = new Scanner(System.in);
		System.out.println("Enter number of processes");
		NumOfProcesses = input.nextInt();

		System.out.println("Enter the process name || Burst time || arrival time");
		for (int i = 0; i < NumOfProcesses; i++) {
			String processName = input.next();
			int burst = input.nextInt();
			int arrival = input.nextInt();
			Process p = new Process();
			p.name = processName;
			p.burstTime = p.CopyBurstTime = burst;
			p.arrivalTime = arrival;
			processesList.add(p);

		}

		System.out.println("Enter Time Quantum");
		Quantum = input.nextInt();

		System.out.println("Enter the context Switching");
		context_Switching = input.nextInt();

		Collections.sort(processesList, new sortByArrivalTime());

	}

	void runRR() {
		GetData();
		executeProcess();
		print_output();

	}

	void executeProcess() {
		endTime.add(0);
		int quantumTimer = 0;
		Process prevProcess = null;
		for (timer = 0; NumOfProcesses > 0; timer++) {

			for (int i = 0; i < processesList.size(); i++) { /// if process arrived, add it to its queue
				Process p = processesList.get(i);
				if (p.arrivalTime == timer) {

					RRqueue.add(p);

				}
			}
			if (prevProcess != null) {
				RRqueue.add(prevProcess);
				prevProcess = null;
			}
			if (RRqueue.size() > 0) {
				Process p = RRqueue.peek();
				if (excudeOrder.size() == 0 || excudeOrder.get(excudeOrder.size() - 1) != p) {
					excudeOrder.add(p);
				}
				p.CopyBurstTime--;
				quantumTimer++;
				if (p.CopyBurstTime == 0) { // process ended
					p.turnAroundTime = (timer + 1) - p.arrivalTime;
					p.waitingTime = p.turnAroundTime - p.burstTime;
					quantumTimer = 0;
					prevProcess = null;
					RRqueue.poll();
					NumOfProcesses--;
					processesList.set(processesList.indexOf(p), p);

					continue;
				}
				else if (timer > 0 && quantumTimer % Quantum == 0) {
					if (RRqueue.size() > 1) {
//                           RRqueue.add(p);
						prevProcess = p;

						RRqueue.poll();
						continue;
					}

				}

			}

		}

		for (int k = 0; k < processesList.size(); k++) {
			int n = 0;
			Process p = processesList.get(k);
			for (int u = 0; u < excudeOrder.size(); u++) {
				if (p.name.equals(excudeOrder.get(u).name))
					n = u;
			}

			p.turnAroundTime += n * context_Switching;
			p.waitingTime += n * context_Switching;

			processesList.set(processesList.indexOf(p), p);

		}
	}

	void print_output() {
		for (int i = 0; i < excudeOrder.size(); i++) {
			System.out.print(excudeOrder.get(i).name + "  ");
		}
		System.out.println();

		System.out.println("Process Name    Turnaround Time   Waiting Time ");
		for (int i = 0; i < processesList.size(); i++) {
			Process p = processesList.get(i);
			averageTurnAroundTime += p.turnAroundTime;
			averageWaitingTime += p.waitingTime;
			System.out.println(" " + p.name + "     \t    " + p.turnAroundTime + "    \t     " + p.waitingTime);
		}

		averageTurnAroundTime /= processesList.size();
		averageWaitingTime /= processesList.size();

		System.out.println("Average Turnaround Time = " + averageTurnAroundTime);
		System.out.println("Average WaitingTime = " + averageWaitingTime);
	}
}
