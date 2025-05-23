/*xml
<module name="Checker">
  <module name="TreeWalker">
    <module name="IllegalTokenText">
      <property name="tokens" value="NUM_INT,NUM_LONG"/>
      <property name="format" value="^0[^lx]"/>
      <property name="ignoreCase" value="true"/>
    </module>
  </module>
</module>
*/

package com.puppycrawl.tools.checkstyle.checks.coding.illegaltokentext;

// xdoc section -- start
public class Example4 {
  public void myTest() {
    int test1 = 0;
    int test2 = 0x111;
    int test3 = 0X111; // ok, case is ignored
    int test4 = 010; // violation
    long test5 = 0L;
    long test6 = 010L; // violation
  }
}
// xdoc section -- end
