import { StorybookCanvas } from "@smartb/archetypes-ui-documentation";

export const parameters = {
  options: {
    storySort: {
      order: [
        "Introduction",
        "SSM-TX",
        ["General", "Configuration", "SSM", "Session"],
        "SSM-CHAINCODE",
        "SSM-COUCHDB",
        ["General", "Configuration", "Model", "Query functions"],
      ]
    }
  },
  docs: {
    container: StorybookCanvas
  },
}
