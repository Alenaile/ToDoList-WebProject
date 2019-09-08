package by.gsu.epamlab.controllers.enums;

import by.gsu.epamlab.controllers.commands.file.UploadFileCommand;
import by.gsu.epamlab.controllers.commands.file.DeleteFileCommand;
import by.gsu.epamlab.controllers.commands.file.DownloadFileCommand;
import by.gsu.epamlab.controllers.commands.tasks.*;
import by.gsu.epamlab.controllers.commands.tasks.updatable.FixUpdateCommand;
import by.gsu.epamlab.controllers.commands.tasks.updatable.PermDeleteUpdateCommand;
import by.gsu.epamlab.controllers.commands.tasks.updatable.RecoverUpdateCommand;
import by.gsu.epamlab.controllers.commands.tasks.updatable.RemoveUpdateCommand;
import by.gsu.epamlab.controllers.interfaces.ActionCommand;
import by.gsu.epamlab.controllers.commands.user.LoginCommand;
import by.gsu.epamlab.controllers.commands.user.LogoutCommand;
import by.gsu.epamlab.controllers.commands.user.RegistrationCommand;

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
            return new FixUpdateCommand();
        }
    },

    REMOVE {
        @Override
        public ActionCommand getCurrentCommand() {
            return new RemoveUpdateCommand();
        }
    },

    RECOVER {
        @Override
        public ActionCommand getCurrentCommand() {
            return new RecoverUpdateCommand();
        }
    },

    DELETE {
        @Override
        public ActionCommand getCurrentCommand() {
            return new PermDeleteUpdateCommand();
        }
    },

    CLEAR {
        @Override
        public ActionCommand getCurrentCommand() {
            return new PermDeleteAllCommand();
        }
    },

    UPLOAD {
        @Override
        public ActionCommand getCurrentCommand() {
            return new UploadFileCommand();
        }
    },

    DOWNLOAD {
        @Override
        public ActionCommand getCurrentCommand() {
            return new DownloadFileCommand();
        }
    },

    DEL {
        @Override
        public ActionCommand getCurrentCommand() {
            return new DeleteFileCommand();
        }
    };

    public abstract ActionCommand getCurrentCommand();
}
