
# XPath 예시 
```bash
driver.elementByXPath("//android.widget.Button 56[contains(@text, ‘NEXT’)]")

driver.elementByXPath("//android.widget.Button 56[contains(@resource-id, ‘com.path.eniwhere:id/btn_next’)]")

driver.elementByXPath("//android.widget.Button 56[contains(@resource-id, ‘com.path.eniwhere:id/btn_next’) and @text=‘NEXT’]")
```
