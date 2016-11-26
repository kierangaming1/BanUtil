package xyz.sidetrip.commands;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;
import xyz.sidetrip.commands.wizard.InputWizard;
import xyz.sidetrip.commands.wizard.WizardQuestions;
import xyz.sidetrip.events.OnCommandEvent;

public class CommandListener implements IListener<OnCommandEvent> {

	@Override
	public void handle(OnCommandEvent event) {
		//Messy test code!
		String testReply = "**Command:** " + event.getCommand()
				+ "\n **Args length:** " + event.getArgs().length
				+ "\n **Received arguments:** ";
		int count = 0;
		for (String argument : event.getArgs()) {
			testReply += "``" + argument + "`` ";
			count++;
		}
		if (count == 0)
			testReply += "None";
		try {
			event.getMessage().reply(testReply);
		} catch (MissingPermissionsException | RateLimitException
				| DiscordException e) {
			e.printStackTrace();
		}
		if (event.matchesCommand("wizard")) {
			try {
				event.getMessage().reply("Starting test wizzard!");
			} catch (MissingPermissionsException | RateLimitException
					| DiscordException e) {
				e.printStackTrace();
			}
			event.getClient().getDispatcher().registerListener(new InputWizard(
					event.getMessage().getChannel(),
					event.getMessage().getAuthor(), WizardQuestions.TEST_QUESTIONS));
		}

	}

}