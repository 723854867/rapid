package org.rapid.util.bean.enums;

public enum CacheUnit {

	B,
	KB {
		@Override
		public long bytes() {
			return 1024;
		}
	},
	MB {
		@Override
		public long bytes() {
			return 1048576;
		}
	},
	GB {
		@Override
		public long bytes() {
			return 1073741824;
		}
	};
	
	public long bytes() {
		return 1;
	}
}
