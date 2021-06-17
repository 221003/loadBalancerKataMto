package edu.iis.mto.serverloadbalancer;

public class ServerBuilder implements Builder<Server> {

	private int capacity;
	private double initialLoad;

	public ServerBuilder withCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}

	public Server build() {
		Server server = new Server(capacity);
		addInitilaLoad(server);
		return server;
	}

	private void addInitilaLoad(Server server) {
		if(initialLoad > 0) {
			int initialVmSize = (int) (initialLoad / (double)capacity * Server.MAXIMUM_LOAD);
			Vm initialVm = VmBuilder.vm().ofSize(initialVmSize).build();
			server.addVm(initialVm);
		}
	}
	
	public static ServerBuilder server() {
		return new ServerBuilder();
	}

	public ServerBuilder withCurrentLoadOf(double initalLoad) {
		this.initialLoad = initalLoad;
		return this;
	}

}
