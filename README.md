# SalesAggregate

A small message processing application that consumes different Sales notification messages and processes them

Actions Performed

* After every 10th message received the application prints a report detailing the number of sales of each product and
  their total value.

* After 50 messages the application prints that it is pausing, stops accepting new messages and log a report of the
  adjustments that have been made to each sale type while the application was running.

Different Sales Types

    * SALE 
    * ADJUSTMENT

Sample Structure of the expected sales Message

*       {
            "qty": 1,
            "salesType": "SALE",
            "unitPrice": 10,
            "productType": "APPLE"
        }

Sample Structure of the expected Adjustment Message

*        {
            "salesType": "ADJUSTMENT",
            "productType": "APPLE",
            "adjustmentPrice": 5,
            "productAdjustment": "MULTIPLY"
          }

External Libraries Used:

*       Junit --> scope is test

        GSON --> used to read json from the file. can be removed when replaced by actual producer

To Run the application:

* Provide sample data in src/main/resources/SalesData.json file and run the application

Additional Dev:

*     the consumer can start consuming from actual Topic/Queue in real env
