package general;

import org.newdawn.slick.Input;

public class AppInput extends Input {

	// TODO: remapping
	public static final int BUTTON_A = 1;
	public static final int BUTTON_B = 2;
	public static final int BUTTON_X = 4;
	public static final int BUTTON_Y = 8;
	public static final int BUTTON_L = 16;
	public static final int BUTTON_R = 32;
	public static final int BUTTON_MINUS = 64;
	public static final int BUTTON_PLUS = 128;
	public static final int BUTTON_SL = 256;
	public static final int BUTTON_SR = 512;
	// public static final int BUTTON_ZL = 1024;
	// public static final int BUTTON_ZR = 2048;
	// public static final int BUTTON_UP = 4096;
	// public static final int BUTTON_DOWN = 80192;
	// public static final int BUTTON_LEFT = 16384;
	// public static final int BUTTON_RIGHT = 32768;
	public static final int AXIS_YL = 0;
	public static final int AXIS_XL = 1;
	public static final int AXIS_YR = 2;
	public static final int AXIS_XR = 3;

	private static final int BUTTON_COUNT = 10; /* Magic number */

	public AppInput (int height) {
		super (height);
	}

	@Override
	public boolean isButtonPressed (int index, int controller) {
		for (int i = 0; i < AppInput.BUTTON_COUNT; i++) {
			if ((index & 1) != 0 && super.isButtonPressed (i, controller)) {
				return true;
			}
			index >>>= 1;
		}
		return false;
	}

	@Override
	public boolean isControlPressed (int button) {
		for (int i = 0; i < AppInput.BUTTON_COUNT; i++) {
			if ((button & 1) != 0 && super.isControlPressed (button)) {
				return true;
			}
			button >>>= 1;
		}
		return false;
	}

	@Override
	public boolean isControlPressed (int button, int controller) {
		for (int i = 0; i < AppInput.BUTTON_COUNT; i++) {
			if ((button & 1) != 0 && super.isControlPressed (button, controller)) {
				return true;
			}
			button >>>= 1;
		}
		return false;
	}

	public boolean areButtonsPressed (int index, int controller) {
		for (int i = 0; i < AppInput.BUTTON_COUNT; i++) {
			if ((index & 1) != 0 && !super.isButtonPressed (i, controller)) {
				return false;
			}
			index >>>= 1;
		}
		return true;
	}

	public boolean areControlsPressed (int button) {
		for (int i = 0; i < AppInput.BUTTON_COUNT; i++) {
			if ((button & 1) != 0 && !super.isControlPressed (button)) {
				return false;
			}
			button >>>= 1;
		}
		return true;
	}

	public boolean areControlsPressed (int button, int controller) {
		for (int i = 0; i < AppInput.BUTTON_COUNT; i++) {
			if ((button & 1) != 0 && !super.isControlPressed (button, controller)) {
				return false;
			}
			button >>>= 1;
		}
		return true;
	}

	public int getButtonCount () {
		return AppInput.BUTTON_COUNT;
	}

}
