package by.gsu.epamlab.enums;

import by.gsu.epamlab.controllers.tasks.command.*;
import by.gsu.epamlab.controllers.user.command.LoginCommand;
import by.gsu.epamlab.controllers.user.command.LogoutCommand;
import by.gsu.epamlab.controllers.user.command.RegistrationCommand;
import by.gsu.epamlab.interfaces.ActionCommand;

public enum ActionType {
    LOGIN {
        @Override
        public ActionCommand getCurrentCommand() {
            return new LoginCommand();
        }

    },
    REGISTRATION {
        @Override
        public ActionCommand getCurrentCommand() {
            return new RegistrationCommand();
        }

    },
    LOGOUT {
        @Override
        public ActionCommand getCurrentCommand() {
            return new LogoutCommand();
        }

    },
    ADD {
        @Override
        public ActionCommand getCurrentCommand() {
            return new AddCommand();
        }
    },
    FIX {
        @Override
        public ActionCommand getCurrentCommand() {
            return new FixCommand();
        }
    },
    REMOVE {
        @Override
        public ActionCommand getCurrentCommand() {
            return new RemoveCommand();
        }
    },
    RECOVER {
        @Override
        public ActionCommand getCurrentCommand() {
            return new RecoverCommand();
        }
    },
    DELETE {
        @Override
        public ActionCommand getCurrentCommand() {
            return new PermDeleteCommand();
        }
    };


    public abstract ActionCommand getCurrentCommand();
}
