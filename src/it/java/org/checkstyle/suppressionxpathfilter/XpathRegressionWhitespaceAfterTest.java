///////////////////////////////////////////////////////////////////////////////////////////////
// checkstyle: Checks Java source code and other text files for adherence to a set of rules.
// Copyright (C) 2001-2025 the original author or authors.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
///////////////////////////////////////////////////////////////////////////////////////////////

package org.checkstyle.suppressionxpathfilter;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.checks.whitespace.WhitespaceAfterCheck;

public class XpathRegressionWhitespaceAfterTest extends AbstractXpathTestSupport {

    private final String checkName = WhitespaceAfterCheck.class.getSimpleName();

    @Override
    protected String getCheckName() {
        return checkName;
    }

    @Test
    public void testWhitespaceAfterTypecast() throws Exception {
        final File fileToProcess =
                new File(getPath("InputXpathWhitespaceAfterTypecast.java"));

        final DefaultConfiguration moduleConfig =
                createModuleConfig(WhitespaceAfterCheck.class);

        final String[] expectedViolation = {
            "4:25: " + getCheckMessage(WhitespaceAfterCheck.class,
                    WhitespaceAfterCheck.MSG_WS_TYPECAST, "-"),
        };

        final List<String> expectedXpathQueries = Collections.singletonList(
            "/COMPILATION_UNIT/CLASS_DEF[./IDENT["
                + "@text='InputXpathWhitespaceAfterTypecast']]/OBJBLOCK"
                + "/VARIABLE_DEF[./IDENT[@text='bad']]/ASSIGN/EXPR/TYPECAST/RPAREN"
        );

        runVerifications(moduleConfig, fileToProcess, expectedViolation,
                expectedXpathQueries);
    }

    @Test
    public void testWhitespaceAfterNotFollowed() throws Exception {
        final File fileToProcess =
                new File(getPath("InputXpathWhitespaceAfterNotFollowed.java"));

        final DefaultConfiguration moduleConfig =
                createModuleConfig(WhitespaceAfterCheck.class);

        final String[] expectedViolation = {
            "4:19: " + getCheckMessage(WhitespaceAfterCheck.class,
                    WhitespaceAfterCheck.MSG_WS_NOT_FOLLOWED, ","),
        };

        final List<String> expectedXpathQueries = Collections.singletonList(
            "/COMPILATION_UNIT/CLASS_DEF[./IDENT["
                + "@text='InputXpathWhitespaceAfterNotFollowed']]/OBJBLOCK"
                + "/VARIABLE_DEF[./IDENT[@text='bad']]/ASSIGN/ARRAY_INIT/COMMA"
        );

        runVerifications(moduleConfig, fileToProcess, expectedViolation,
                expectedXpathQueries);
    }

}
