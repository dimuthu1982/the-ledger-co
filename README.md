<h3>Assumptions</h3>
<ul>
<li>Transactions in the file needs to be in the order of their emi for a particular bank and borrower.</li>
</ul>

<h3>Build</h3>
<code>mvn clean install -DskipTests -q assembly:single</code>

<h3>Run test</h3>
<code>mvn clean test</code>

<h3>Test Coverage</h3>
<code>mvn jacoco:report</code>

<h4>Path</h4>
<code>geektrust-problems/target/site/jacoco/index.html</code>

