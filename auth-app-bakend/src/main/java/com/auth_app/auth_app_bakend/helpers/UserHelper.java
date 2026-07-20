package com.auth_app.auth_app_bakend.helpers;

import java.util.UUID;

public class UserHelper {

    public static UUID parseUUID(String uuid) {
        return UUID.fromString(uuid);
    }
}
