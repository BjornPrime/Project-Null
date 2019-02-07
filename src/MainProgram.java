import bankapp.view.MainView;
import bankapp.view.View;

public class MainProgram {

	public static void main(String[] args) {
		
		View view = new MainView();
		
		do {
			view.showMenu();
			view.getUserInput();
			view = view.process();
		} while (view != null);
		
	}
}