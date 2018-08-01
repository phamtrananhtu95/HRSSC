package com.hrssc.domain;

import java.util.Map;

public class Constant {
	public class CompanyStatus {
		public static final int ACTIVATED = 1;
		public static final int DEACTIVATED = 2;
	}

	public class UserStatus {
		public static final int ACTIVATED = 1;
		public static final int DEACTIVATED = 2;
	}

	public class ProjectProcess {
		public static final int PENDING = 1;
		public static final int ON_GOING = 2;
		public static final int FINISHED = 3;
	}

	public class RequestStatus {
		public static final int OPENNING = 1;
		public static final int CLOSED = 2;
		public static final int REMOVED = 3;
	}

	public class ResourceStatus {
		public static final int AVAILABLE = 1;
		public static final int INACTIVE = 2;
		public static final int BUSY = 3;
	}
	public class ManagerStatus {
		public static final int ACTIVATED = 1;
		public static final int DEACTIVATED = 2;
	}

	public class Time{
		public static final int SECOND = 1;        // no. of ms in a second
		public static final int MINUTE = SECOND * 60; // no. of ms in a minute
		public static final int HOUR = MINUTE * 60;   // no. of ms in an hour
		public static final int DAY = HOUR * 24;      // no. of ms in a day
		public static final int WEEK = DAY * 7;
		public static final int MONTH = DAY * 30;
	}

	public class InteractionType{
		public static final String MATCH = "Match";
		public static final String APPLY = "Apply";
		public static final String INVITE = "Invite";
	}
	public class UserRole{
		public static final int ADMIN = 1;
		public static final int CHIEF = 2;
		public static final int MANAGER = 3;
	}

	public class JobStatus{
		public static final int PENDING = 1;
		public static final int ON_GOING = 2;
		public static final int FINISHED = 3;
		public static final int CANCEL = 4;
	}
	public class NotificationType{
		public static final String APPLY = "Apply";
		public static final String INVITE = "Invite";
		public static final String OFFER_DEALING = "Offer Dealing";
		public static final String FEEDBACK_PENDING = "Feedback Pending";
		public static final String FEEDBACK_COMMITTED = "Feedback Committed";
		public static final String RESOURCE_RELEASED = "Resource Released";

	}

	public static final int MANAGER_ROLE_ID = 3;
	public static final String COMMA = ",";

}
