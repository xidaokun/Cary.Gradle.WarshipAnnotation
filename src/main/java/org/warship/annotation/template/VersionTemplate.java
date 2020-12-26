package org.warship.annotation.template;

import org.warship.annotation.interfaces.Author;
import org.warship.annotation.interfaces.Branch;
import org.warship.annotation.interfaces.CommitId;
import org.warship.annotation.interfaces.Date;
import org.warship.annotation.interfaces.Description;
import org.warship.annotation.interfaces.Version;
import org.warship.annotation.interfaces.VersionCode;
import org.warship.annotation.interfaces.VersionName;

@Version
public interface VersionTemplate {

	@Author
	String authorM();

	@Branch
	String branchM();

	@CommitId
	String commitIdM();

	@Date
	String dateM();

	@VersionCode
	int versionCodeM();

	@VersionName
	String versionNameM();

	@Description
	String descriptionM();

}
